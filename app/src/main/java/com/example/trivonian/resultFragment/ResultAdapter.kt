package com.example.trivonian.resultFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trivonian.R
import com.example.trivonian.dataclasses.Question
import org.w3c.dom.Text

class ResultAdapter(
    private val questionText: String,
    private val userAnswer: String,
    private val correctAnswer: String
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.resultQuestion).text = questionText
        holder.itemView.findViewById<TextView>(R.id.resultUserAnswer).text = userAnswer
        holder.itemView.findViewById<TextView>(R.id.resultQuestionAsnwer).text = correctAnswer
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}