package com.index197511.memo.memopage

//MemoPageFragmentArgs
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import com.index197511.memo.databinding.MemoPageFragmentBinding
import com.index197511.memo.ext.closeKeyboard

class MemoContentFragment : Fragment() {

    private val args: MemoContentFragmentArgs by navArgs()
    private lateinit var memoBinding: MemoPageFragmentBinding
    private lateinit var memoContentFragmentViewModel: MemoContentFragmentViewModel
    private lateinit var sentMemo: Memo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        memoBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.memo_page_fragment, container, false
            )

        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDatabaseDao
        sentMemo = args.content

        val viewModelFactory =
            MemoContentFragmentViewModelFactory(
                dataSource,
                application
            )

        memoContentFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(MemoContentFragmentViewModel::class.java)

        memoBinding.apply {
            memoTitleView.setText(sentMemo.memoTitle)
            memoContentView.setText(sentMemo.memoContent)
        }

        setHasOptionsMenu(true)
        return memoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        sentMemo.apply {
            memoTitle = memoBinding.memoTitleView.text.toString()
            memoContent = memoBinding.memoContentView.text.toString()
        }

        memoContentFragmentViewModel.reflectMemoChange(sentMemo)
        closeKeyboard()

        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

}
