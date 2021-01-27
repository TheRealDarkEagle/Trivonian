package com.example.trivonian.questionApi.datacleaner

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class HtmlCharacterCorrector: DataCleaner {

    override suspend fun clean(text: String): String = withContext(IO) {
        replaceEncodingIssus(text)
    }

    private fun replaceEncodingIssus(text: String): String {
        var cleanedText = text
        htmlCharacterMapper.map {
            cleanedText = replace(cleanedText, it.key, it.value)
        }
        return cleanedText
    }

    private fun replace(text: String, incorrectText: String, replacement: String): String {
        return text.replace(incorrectText, replacement)
    }

    companion object {
        val htmlCharacterMapper: Map<String, String>
            get() = mapOf("&#039;" to "´",
                        "&quot;" to "\\\"",
                        "&amp;" to "&",
                        "&eacute;" to "é")
    }
}