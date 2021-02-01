package com.example.trivonian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.trivonian.gameFragment.GameFragmentViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: GameFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}