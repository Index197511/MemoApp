package com.index197511.memo.memopage

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.MemoPageFragmentBinding
import com.index197511.memo.ext.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoContentFragment : Fragment() {

    private val viewModel by viewModels<MemoContentFragmentViewModel>()
    private val args: MemoContentFragmentArgs by navArgs()
    private lateinit var binding: MemoPageFragmentBinding
    private lateinit var sentMemo: Memo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sentMemo = args.content
        binding = MemoPageFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            memoTitleView.setText(sentMemo.title)
            memoContentView.setText(sentMemo.content)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val updatedMemo = Memo(
            id = args.content.id,
            title = binding.memoTitleView.text.toString(),
            content = binding.memoContentView.text.toString()
        )

        viewModel.updateMemo(updatedMemo)
        closeKeyboard()

        return view?.let { view ->
            NavigationUI.onNavDestinationSelected(item, view.findNavController())
        } ?: super.onOptionsItemSelected(item)

    }

}
