package com.example.trivonian.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater, null, false)
    }
    private val viewModel by activityViewModels<ResultFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.resultRecyclerView)
        recyclerView.adapter = ResultAdapter(viewModel.questionList, viewModel.answerList)
    }

}