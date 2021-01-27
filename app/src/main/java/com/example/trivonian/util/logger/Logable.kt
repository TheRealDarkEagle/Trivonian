package com.example.trivonian.util.logger

import android.util.Log

interface Logable {

    fun logInformation(msg: String) {
        val loggerPrefix = "TRIVIA#"
        Log.i(loggerPrefix+this.javaClass.name, msg)
    }
}