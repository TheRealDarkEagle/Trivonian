package com.example.trivonian.resultFragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R
import com.example.trivonian.databinding.TextItemViewBinding
import com.example.trivonian.dataclasses.Question
import org.w3c.dom.Text

class ResultAdapter(
    private val questions: List<Question>,
    private val userAnswers: List<String>
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TextItemViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.text_item_view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position], userAnswers[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class ViewHolder(private val binding: TextItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question, userAnswer: String) {
            binding.question = question
            binding.answer = userAnswer

            if (question.correctAnswer == userAnswer) {
                binding.resultUserAnswer.setBackgroundColor(Color.GREEN)
            } else {
                binding.resultUserAnswer.setBackgroundColor(Color.RED)
            }
        }

    }


}