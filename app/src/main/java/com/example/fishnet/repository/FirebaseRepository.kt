package com.example.fishnet.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fishnet.data.FlashCardData
import com.example.fishnet.data.UserData
import com.example.fishnet.data.CardGroupData
import com.example.fishnet.data.UserCardData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class FirebaseRepository {

    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()


    fun createNewUser(user: UserData){
        cloud.collection("user")
            .document(user.userId!!)
            .set(user)
    }

    fun getUserData(): LiveData<UserData> {
        val cloudResult = MutableLiveData<UserData>()
        val userId = auth.currentUser?.uid

        cloud.collection("user")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(UserData::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getUserData(userId: String): LiveData<UserData> {
        val cloudResult = MutableLiveData<UserData>()
        val userId = userId

        cloud.collection("user")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                val user = it.toObject(UserData::class.java)
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getFlashCardsData(groupId: String) :LiveData<List<FlashCardData>> {
        val cloudResult = MutableLiveData<List<FlashCardData>>()
        cloud.collection("flashCard")
            .whereEqualTo("groupId",groupId)
            .get()
            .addOnSuccessListener {
                Log.d(REPO_DEBUG, it.toString())
                val flashCard = it.toObjects(FlashCardData::class.java)
                Log.d(REPO_DEBUG, flashCard.toString())
                cloudResult.postValue(flashCard)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getCardGroupData(list: List<String>?) :LiveData<List<CardGroupData>> {
        val cloudResult = MutableLiveData<List<CardGroupData>>()

        if(!list.isNullOrEmpty()) {
            cloud.collection("cardGroup")
                .whereIn("groupId", list)
                .get()
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, it.toString())
                    val cardGroup = it.toObjects(CardGroupData::class.java)
                    Log.d(REPO_DEBUG, cardGroup.toString())
                    cloudResult.postValue(cardGroup)
                }
                .addOnFailureListener {
                    Log.d(REPO_DEBUG, it.message.toString())
                }
        }
        return cloudResult
    }

    fun addUserGroup(groupId: String){
        cloud.collection("user")
            .document(auth.currentUser?.uid!!)
            .update("cardGroupsId",FieldValue.arrayUnion(groupId))
            .addOnSuccessListener {
                Log.d(REPO_DEBUG,"Dodano grupe do uzytkownika")
            }.addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun createCardGroup(cardGroup: CardGroupData) {
        cloud.collection("cardGroup")
            .add(cardGroup)
            .addOnSuccessListener {
                it.update("groupId",it.id)
                addUserGroup(it.id)
                Log.d(REPO_DEBUG,"Utworzono grupe")
            }.addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }

    fun createFlashCard(flashCard: FlashCardData){
        cloud.collection("flashCard")
            .add(flashCard)
            .addOnSuccessListener {
                it.update("cardId",it.id)
                addUserGroup(it.id)
                Log.d(REPO_DEBUG,"Utworzono flashCard")
            }.addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }


    fun getCardUser(cardId: String) : LiveData<List<UserCardData>>? {



        val cloudResult = MutableLiveData<List<UserCardData>>()
            cloud.collection("userCard")
                .whereEqualTo("userId",auth.currentUser?.uid!!)
                .whereEqualTo("cardId",cardId)
                .get()
                .addOnSuccessListener {
                    val cardGroup = it.toObjects(UserCardData::class.java)
                    Log.d(REPO_DEBUG, cardGroup.toString())
                    Log.d("ADD_USER_CARD","POBIERAM USER CARD "+cardGroup.toString())
                    cloudResult.postValue(cardGroup)
                }
                .addOnFailureListener {
                    Log.d(REPO_DEBUG, it.message.toString())
                }

        Log.d("ADD_USER_CARD","RETURNUJE USER CARD "+cloudResult.toString())
        return cloudResult
    }


    fun updateUserCard(cardId: String, point: Int) {

        var userCardOld: UserCardData
        var newStatus: Int? = null

        cloud.collection("userCard")
            .whereEqualTo("userId", auth.currentUser?.uid!!)
            .whereEqualTo("cardId", cardId)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    userCardOld = document.toObject(UserCardData::class.java)
                    if (userCardOld.cardUserId != null) {

                        if (userCardOld.status!! + point < 0 || userCardOld.status!! + point > 5) {
                            newStatus = userCardOld.status
                        } else {
                            newStatus = userCardOld.status!! + point
                        }

                        cloud.collection("userCard")
                            .document(userCardOld.cardUserId!!)
                            .update("status", newStatus)
                            .addOnSuccessListener {
                                Log.d(REPO_DEBUG, "Zaktualizowano status")
                            }
                            .addOnFailureListener {
                                Log.d(REPO_DEBUG, it.message.toString())
                            }
                    }
                }
                if (it.isEmpty) {
                    val userCard = UserCardData(
                        "",
                        auth.currentUser?.uid!!,
                        cardId,
                        if (point == 1) 2 else 1
                    )


                    cloud.collection("userCard")
                        .add(userCard)
                        .addOnSuccessListener {
                            Log.d("ADD_USER_CARD", "Utworzono userCard BEZ ID")
                            it.update("cardUserId", it.id)
                            Log.d("ADD_USER_CARD", "Utworzono userCard")
                        }.addOnFailureListener {
                            Log.d("ADD_USER_CARD", it.message.toString())
                        }
                }
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
    }
}