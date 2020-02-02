package com.index197511.memo.addmemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class AddMemoFragmentViewModel(
    application: Application
) :
    AndroidViewModel(application) {
    private var memoRepository: MemoRepository = MemoRepository.getInstance(application)

    fun insertMemoToDatabase(memo: Memo) {
        runBlocking {
            memoRepository.insert(memo)
        }
    }

}
