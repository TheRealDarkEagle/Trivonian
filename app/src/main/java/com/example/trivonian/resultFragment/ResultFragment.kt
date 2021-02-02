package com.example.trivonian.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R
import com.example.trivonian.databinding.FragmentResultBinding
import com.example.trivonian.util.logger.Logable


class ResultFragment : Fragment(), Logable {

    lateinit var recyclerView: RecyclerView
    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater, null, false)
    }
    private val viewModel by activityViewModels<ResultFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recyclerView = binding.resultRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAnswers = viewModel.answerList.value
        val questions = viewModel.questionList.value

        binding.playAgainButton.setOnClickListener {
            navigateToStartFragment()
        }

        if (questions != null && userAnswers != null) {
            logInformation("starting resultadapter with -> ${userAnswers.size} & ${questions.size}")
            val adapter = ResultAdapter(questions, userAnswers)
            recyclerView.adapter = adapter
        }
    }

    private fun navigateToStartFragment() {
        val action = ResultFragmentDirections.actionResultFragmentToStartFragment()
        findNavController().navigate(action)
    }

}