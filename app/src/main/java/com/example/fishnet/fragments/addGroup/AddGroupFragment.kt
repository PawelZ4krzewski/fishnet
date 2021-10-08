package com.example.fishnet.fragments.addGroup

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishnet.R
import com.example.fishnet.databinding.FragmentAddGroupBinding

class AddGroupFragment : Fragment() {

    private val addGroupVM: AddGroupViewModel by viewModels<AddGroupViewModel>()
    private var _binding: FragmentAddGroupBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddGroupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.groupDescriptionEditText.doOnTextChanged { text, _, _, _ ->
            addGroupVM.setGroupName(text.toString())
        }
        binding.groupNameEditText.doOnTextChanged { text, _, _, _ ->
            addGroupVM.setGroupDescription(text.toString())
        }

        binding.addGroupButton.setOnClickListener{
            addGroupVM.addCardGroup()
            Toast.makeText(context, "Utworzono grupe", LENGTH_SHORT).show()
        }

        binding.backtoListGroupButton.setOnClickListener {
            findNavController().navigate(AddGroupFragmentDirections.actionAddGroupFragmentToListGroupFishFragment())
        }


    }

}