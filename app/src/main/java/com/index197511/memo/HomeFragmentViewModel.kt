package com.index197511.memo

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.*

class HomeFragmentViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val memos = database.getAllMemo()

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
            val memo = getMemoFromDatabase(position) ?: mockContent
            Log.i("HomeFragmentViewModel", "onItemClick after getFromDatabase")
            val action = HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(memo)
            findNavController(tappedView).navigate(action)
        }

    }
}

//val mockContent = Memo(memoTitle = "mock", memoContent = "mock")
//val content = getMemoFromDatabase(position)
//
//Log.i("HomeFragment", "Success database access")
//val action = HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(content)
//findNavController().navigate(action)