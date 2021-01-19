package com.example.trivonian.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R


class ResultFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.resultRecyclerView)
        recyclerView.adapter = ResultAdapter(args.question, args.userAnswer, args.correctAnswer)
        //Fertigstellung der RecyclerView https://www.youtube.com/watch?v=18VcnYN5_LM
    }

}