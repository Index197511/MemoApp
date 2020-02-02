package com.index197511.memo.memopage

//MemoPageFragmentArgs
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
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
        sentMemo = args.content
        memoBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.memo_page_fragment, container, false
            )

        val application = requireNotNull(this.activity).application

        val viewModelFactory = MemoContentFragmentViewModelFactory(application)

        memoContentFragmentViewModel =
            ViewModelProvider(this, viewModelFactory)
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

        memoContentFragmentViewModel.updateMemo(sentMemo)
        closeKeyboard()

        return view?.let { view ->
            NavigationUI.onNavDestinationSelected(item, view.findNavController())
        } ?: super.onOptionsItemSelected(item)

    }

}
