package dev.mitigate.dogapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dev.mitigate.dogapp.R
import dev.mitigate.dogapp.adapters.DogListAdapter
import dev.mitigate.dogapp.databinding.FragmentDogListBinding
import dev.mitigate.dogapp.viewmodels.DogListViewModel
import timber.log.Timber

class DogListFragment: Fragment() {

    private val viewModel by viewModels<DogListViewModel>()
    private lateinit var binding: FragmentDogListBinding
    private val adapter = DogListAdapter {model ->
        val action = DogListFragmentDirections.actionDogListFragmentToDogDetailsFragment(model)
        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dogRv.adapter = adapter
        viewModel.onDogListLoadedLiveData.observe(viewLifecycleOwner) { dogList ->
            Timber.d("dog list: ${dogList}")


            if (dogList != null) {
                if(dogList.isEmpty()) showInternetError(true) else {
                    binding.swiperefresh.isRefreshing = false

                    adapter.itemList = dogList
                    toggleProgressBar(false)
                }
            }
        }

        Timber.d("start obeserve on progress")
        viewModel.showLoaderLiveData.observe(viewLifecycleOwner) { isVisible ->
            Timber.d("$isVisible")
            toggleProgressBar(isVisible)
        }

        binding.swiperefresh.setOnRefreshListener {
            binding.swiperefresh.isRefreshing = true
            viewModel.loadDogs()

        }

        viewModel.setupView()
    }

    private fun toggleProgressBar(isVisible: Boolean) {
        Timber.d("Updating progress bar to $isVisible")
        binding.progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        binding.dogRv.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun showInternetError(isVisible: Boolean){
        binding.internetError.visibility = if (isVisible) View.VISIBLE else View.GONE
        Timber.d("updating error")
    }
}