package dev.mitigate.dogapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mitigate.dogapp.models.DogModel
import dev.mitigate.dogapp.repository.DogRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class DogListViewModel: ViewModel() {

    private val dogRepository = DogRepository()

    private val _onDogListLoadedLiveData = MutableLiveData<List<DogModel>?>()
    val onDogListLoadedLiveData: LiveData<List<DogModel>?> = _onDogListLoadedLiveData

    private val _showLoaderLiveData = MutableLiveData<Boolean>(true)
    val showLoaderLiveData: LiveData<Boolean> = _showLoaderLiveData

    fun setupView() {
        if (onDogListLoadedLiveData.value == null) {
            loadDogs()
        }
    }



    fun loadDogs() {
        _showLoaderLiveData.value = true
        viewModelScope.launch {
            _onDogListLoadedLiveData.postValue(dogRepository.getDogList())
            _showLoaderLiveData.value = false
        }
        Timber.d("settings loader visibility as false..")
    }

}