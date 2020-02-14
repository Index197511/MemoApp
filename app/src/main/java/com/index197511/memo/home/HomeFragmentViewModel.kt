package com.index197511.memo.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class HomeFragmentViewModel(private val repository: MemoRepository) :
    ViewModel() {
    val allMemoList: LiveData<List<Memo>> = repository.allMemos

    fun deleteFromDatabase(position: Int) {
        runBlocking {
            allMemoList.value
                ?.get(position)
                ?.also { memos -> repository.delete(memos) }
        }
    }

    fun onItemClick(tappedView: View, position: Int) {
        allMemoList.value
            ?.get(position)
            ?.let { memo -> HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(memo) }
            ?.also { action -> findNavController(tappedView).navigate(action) }
    }

}
