package com.index197511.memo.home

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.*

class HomeFragmentViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private lateinit var allMemoList: List<Memo>
    private val _allMemoIdAndTitleList = mutableListOf<Pair<Int, String>>()
    val allMemoIdAndTitleList: List<Pair<Int, String>>
        get() {
            return _allMemoIdAndTitleList
        }

    init {
        updateMemoList()
        Log.i("HomeFragmentViewModel", allMemoList.toString())
        Log.i("HomeFragmentViewModel", allMemoIdAndTitleList.toString())
    }

    fun updateMemoList() {
        setAllMemoToAllMemoList()
        setAllMemoIdAndTitle()
    }

    private fun setAllMemoToAllMemoList() {
        runBlocking {
            val allMemo = getAllMemoFromDatabase()
            allMemoList = allMemo
        }
    }

    private fun setAllMemoIdAndTitle() {
        val allMemoIdAndTitleList = allMemoList
            .map { memo -> memo.memoId to memo.memoTitle }

        _allMemoIdAndTitleList.clear()
        _allMemoIdAndTitleList += allMemoIdAndTitleList
    }

    fun deleteFromDatabase(position: Int) {
        runBlocking {
            delete(allMemoList[position])
        }
        updateMemoList()
        Log.i("HomeFragmentViewModel delete", allMemoList.toString())
        Log.i("HomeFragmentViewModel delete", allMemoIdAndTitleList.toString())
    }

    fun onItemClick(tappedView: View, position: Int) {
        uiScope.launch {
            val memo = allMemoList[position]
            val action =
                HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(
                    memo
                )
            findNavController(tappedView).navigate(action)
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

    private suspend fun delete(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.delete(memo)
        }
    }

    private suspend fun getAllMemoFromDatabase(): List<Memo> {
        return withContext(Dispatchers.IO) {
            database.getAllMemo()
        }
    }

}
