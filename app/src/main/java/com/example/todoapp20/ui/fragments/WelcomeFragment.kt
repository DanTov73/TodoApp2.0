package com.example.todoapp20.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp20.R
import com.example.todoapp20.databinding.FragmentWelcomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val fireStore = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fireStore.collection("user").get().addOnSuccessListener { result ->
            for (document in result) {
                binding.tvWelcome.text = "Welcome \n ${document.get("name")}"
            }
        }.addOnFailureListener { exception ->
            Log.e("firestore", "Error get documents", exception)
        }
        binding.btnApply.setOnClickListener {
            findNavController().navigate(R.id.onBoardingFragment)
        }
    }

}