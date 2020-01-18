package com.index197511.memo.home

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val _allMemoList: MutableList<Memo> = mutableListOf()
    val allMemoList: List<Memo>
        get() {
            return _allMemoList
        }

    init {
        updateMemoList()
    }

    fun updateMemoList() {
        setAllMemoToAllMemoList()
    }

    private fun setAllMemoToAllMemoList() {
        runBlocking {
            val allMemo = getAllMemoFromDatabase()
            _allMemoList.clear()
            _allMemoList += (allMemo)
        }
    }

    fun onItemClick(tappedView: View, position: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(
                allMemoList[position]
            )
        findNavController(tappedView).navigate(action)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun deleteFromDatabase(position: Int) {
        runBlocking {
            delete(allMemoList[position])
        }
        updateMemoList()
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
