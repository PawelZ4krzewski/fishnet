package com.example.fishnet.fragments.addFlashCard

import androidx.lifecycle.ViewModel
import com.example.fishnet.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow

class AddFlashCardViewModel: ViewModel() {
    private val repository = FirebaseRepository()
    private val _question = MutableStateFlow("")
    private val _answer = MutableStateFlow("")

    fun createFlashCard(groupId: String) {
        val flashCard = com.example.fishnet.data.FlashCardData(
        _answer.value,
            "",
            groupId,
        _question.value
        )

        repository.createFlashCard(flashCard)
    }

    fun setQuestion(question: String) {
        _question.value = question
    }

    fun setAnswer(answer: String) {
        _answer.value = answer
    }

    fun getQuestion(): String {
        return _question.value
    }

    fun getAnswer(): String {
        return _answer.value
    }
}