package dev.mitigate.dogapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.mitigate.dogapp.R
import dev.mitigate.dogapp.databinding.FragmentDogDetailsBinding

class DogDetailsFragment: Fragment(){

    private lateinit var binding: FragmentDogDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val model = arguments?.let { DogDetailsFragmentArgs.fromBundle(it).model }
        binding.uiModel = model
    }
}