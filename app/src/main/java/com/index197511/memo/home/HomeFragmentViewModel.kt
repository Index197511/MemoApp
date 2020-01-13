package com.index197511.memo.home

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.*

class HomeFragmentViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _allMemoTitle = MutableLiveData<List<String>>()
    val allMemoTitle: LiveData<List<String>>
        get() = _allMemoTitle

    init {
        runBlocking {
            insert(Memo(memoTitle = "A", memoContent = "selected A"))
            insert(Memo(memoTitle = "B", memoContent = "selected B"))
        }
        initializeScreen()
    }

    private fun initializeScreen() {
        runBlocking {
            _allMemoTitle.value = getAllMemoTitle()

        }
    }

    private suspend fun getAllMemoTitle(): List<String> {
        return withContext(Dispatchers.IO) {
            database.getAlltitle()
        }
    }

    private suspend fun getMemoFromDatabase(id: Int): Memo? {
        return withContext(Dispatchers.IO) {
            val memo = database.get(id)
            memo
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.insert(memo)
        }
    }

    private suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.update(memo)
        }
    }

    private suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            database.delete(id)
        }
    }

    fun onItemClick(tappedView: View, position: Int) {
        uiScope.launch {
            val mockContent = Memo(memoTitle = "mock", memoContent = "mock")
            val memo = getMemoFromDatabase(position + 1) ?: mockContent
            Log.i("HomeFragmentViewModel", "onItemClick after getFromDatabase")
            val action =
                HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(
                    memo
                )
            findNavController(tappedView).navigate(action)
        }

    }
}
