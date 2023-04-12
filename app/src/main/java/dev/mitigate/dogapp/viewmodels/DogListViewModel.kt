package dev.mitigate.dogapp.viewmodels

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

    fun loadDogs() {


        viewModelScope.launch {
            val list = dogRepository.getDogList()
            Timber.e("<<< got the list! ${list}")
            _onDogListLoadedLiveData.postValue(list)
        }
    }

    fun onDogListSet() {
        _onDogListLoadedLiveData.value = null
    }
}