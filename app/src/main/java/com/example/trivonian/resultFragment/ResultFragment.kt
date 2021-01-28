package com.example.trivonian.resultFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var binding: FragmentResultBinding
    lateinit var viewModel: ResultFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        viewModel = ViewModelProvider(this).get(ResultFragmentViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            recyclerView = binding.resultRecyclerView
            recyclerView.adapter = viewModel.questionList.value?.let {
                viewModel.answerList.value?.let { it1 ->
                    ResultAdapter(
                        it,
                        it1
                    )
                }
            }
        }
    }

}