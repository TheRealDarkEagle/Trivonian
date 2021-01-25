package com.example.trivonian.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
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
        recyclerView = view.findViewById(R.id.resultRecyclerView)
        recyclerView.adapter = ResultAdapter(viewModel.questionList, viewModel.answerList)
        //Fertigstellung der RecyclerView https://www.youtube.com/watch?v=18VcnYN5_LM
    }

}