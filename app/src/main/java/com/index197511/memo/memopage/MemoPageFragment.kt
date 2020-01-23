package com.index197511.memo.memopage

//MemoPageFragmentArgs
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
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

class MemoPageFragment : Fragment() {

    private val args: MemoPageFragmentArgs by navArgs()
    private lateinit var memoBinding: MemoPageFragmentBinding
    private lateinit var memoPageFragmentViewModel: MemoPageFragmentViewModel
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
            MemoPageFragmentViewModelFactory(
                dataSource,
                application
            )

        memoPageFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MemoPageFragmentViewModel::class.java)

        memoBinding.also {
            it.memoTitleView.setText(sentMemo.memoTitle)
            it.memoContentView.setText(sentMemo.memoContent)
        }

        setHasOptionsMenu(true)
        return memoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        sentMemo.also {
            it.memoTitle = memoBinding.memoTitleView.text.toString()
            it.memoContent = memoBinding.memoContentView.text.toString()
        }
        memoPageFragmentViewModel.reflectMemoChange(sentMemo)
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)

        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)

    }
}
