package com.index197511.memo.home

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class HomeFragmentViewModel(application: Application) :
    AndroidViewModel(application) {
    private var memoRepository: MemoRepository = MemoRepository.getInstance(application)
    val allMemoList: LiveData<List<Memo>> = memoRepository.allMemos

    fun deleteFromDatabase(position: Int) {
        runBlocking {
            allMemoList.value
                ?.get(position)
                ?.also { memos -> memoRepository.delete(memos) }
        }
    }

    fun onItemClick(tappedView: View, position: Int) {
        allMemoList.value
            ?.get(position)
            ?.let { memo -> HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(memo) }
            ?.also { action -> findNavController(tappedView).navigate(action) }
    }

}
