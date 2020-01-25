package com.index197511.memo.home

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class HomeFragmentViewModel(application: Application, val memoRepository: MemoRepository) :
    AndroidViewModel(application) {
    private val _allMemoList: MutableList<Memo> = mutableListOf()
    val allMemoList: List<Memo>
        get() {
            return _allMemoList
        }

    fun updateMemoList() {
        setAllMemoToAllMemoList()
    }

    fun deleteFromDatabase(position: Int) {
        runBlocking {
            memoRepository.delete(allMemoList[position])
        }
        updateMemoList()
    }

    fun onItemClick(tappedView: View, position: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(
                allMemoList[position]
            )
        findNavController(tappedView).navigate(action)
    }

    private fun setAllMemoToAllMemoList() {
        runBlocking {
            _allMemoList.apply {
                clear()
                addAll(memoRepository.getAllMemoFromDatabase())
            }
        }
    }
}
