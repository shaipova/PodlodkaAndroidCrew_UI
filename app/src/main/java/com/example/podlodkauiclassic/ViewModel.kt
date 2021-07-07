package com.example.podlodkauiclassic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionViewModel : ViewModel() {

    val emptyFavList = emptyList<Session>().toMutableList()

    private var _favList: MutableLiveData<MutableList<Session>> = MutableLiveData(emptyFavList)
    val favList: LiveData<MutableList<Session>>
        get() = _favList


    private var _snackbar: MutableLiveData<Boolean> = MutableLiveData(false)
    val snackbar: LiveData<Boolean>
        get() = _snackbar


    val list = MockSessions.toMutableList()

    fun favListChange(data: MutableList<Session>) {
        _favList.value = data
    }

    fun showSnackbar() {
        _snackbar.value = true
    }

    fun stopSnackbar() {
        _snackbar.value = false
    }

}