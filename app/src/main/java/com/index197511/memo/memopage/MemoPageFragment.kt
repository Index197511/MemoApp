package com.index197511.memo.memopage

//MemoPageFragmentArgs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.index197511.memo.R
import com.index197511.memo.databinding.MemoPageFragmentBinding

class MemoPageFragment : Fragment() {

    private val args: MemoPageFragmentArgs by navArgs()
    private lateinit var memoBinding: MemoPageFragmentBinding
    private lateinit var viewModel: MemoPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        memoBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.memo_page_fragment, container, false
            )

        val memo = args.content
        memoBinding.memoContent.text = memo.memoContent
        
        return memoBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MemoPageViewModel::class.java)
    }


}
