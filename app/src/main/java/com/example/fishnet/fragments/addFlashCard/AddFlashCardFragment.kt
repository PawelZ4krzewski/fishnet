package com.example.fishnet.fragments.addFlashCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fishnet.R
import com.example.fishnet.databinding.FragmentAddFlashCardBinding
import com.example.fishnet.fragments.listGroupFish.ListGroupFishFragmentDirections


class AddFlashCardFragment : Fragment() {

    private val args: AddFlashCardFragmentArgs by navArgs()

    private val addFlashCardVM: AddFlashCardViewModel by viewModels<AddFlashCardViewModel>()

    private var _binding: FragmentAddFlashCardBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddFlashCardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToFlashCardsButton.setOnClickListener {
            val action = AddFlashCardFragmentDirections.actionAddFlashCardFragmentToFishesFragment(args.groupId)
            findNavController().navigate(action)
        }

        binding.answerEditText.doOnTextChanged { text, _, _, _ ->
            addFlashCardVM.setAnswer(text.toString())
        }
        binding.questionEditText.doOnTextChanged { text, _, _, _ ->
            addFlashCardVM.setQuestion(text.toString())
        }

        binding.addFlashCardButton.setOnClickListener{
            addFlashCardVM.createFlashCard(args.groupId)
            Toast.makeText(context, "Utworzono fiszke", Toast.LENGTH_SHORT).show()
        }
    }

}