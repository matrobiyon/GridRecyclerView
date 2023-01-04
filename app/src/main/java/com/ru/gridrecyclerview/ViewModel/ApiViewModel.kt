package com.ru.gridrecyclerview.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ru.gridrecyclerview.model.data.GridData
import com.ru.gridrecyclerview.Api.PhotoApi
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel() {

    var photoUrl = ""

    private val _dataResult = MutableLiveData<List<GridData>>()
    val dataResult : LiveData<List<GridData>>
        get() = _dataResult

    private val _status = MutableLiveData<Boolean>()
    val status : LiveData<Boolean>
        get() = _status

    fun init() {
        viewModelScope.launch {
            try {
                val listResult = PhotoApi.retrofitService.getData()
                _dataResult.value = listResult
                _status.value = true

                //Testing
                Log.d("MyError", "${_dataResult.value} from viewModel")
            } catch (e: Exception) {
                _status.value = false

                //Testing
                Log.d("MyError", "$e from viewModel and Catch block")

            }
        }
    }
}