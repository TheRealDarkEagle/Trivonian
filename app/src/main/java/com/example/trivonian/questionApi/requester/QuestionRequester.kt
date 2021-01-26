package com.example.trivonian.questionApi.requester

import okhttp3.OkHttpClient
import okhttp3.Request

class QuestionRequester : DataRequester {

    //Client über den die Requests ablaufen werden
    private val client: OkHttpClient = OkHttpClient()

    // request darf für später noch angepasst werden
    // für den momentanen lauf ist dies vollkommen okay
    private val request: Request
        get() = Request.Builder()
            .url("https://opentdb.com/api.php?amount=10")
            .build()

    /**
     * Requests the Question from the api
     *
     */
    override fun requestQuestions(): String {
        return getMockedQuestion()
    }


    private fun getMockedQuestion(): String {
        return """
            {"response_code":0,"results":[{"category":"Entertainment: Music","type":"multiple","difficulty":"easy","question":"What is the best selling album of all time from 1976 to 2018?","correct_answer":"Thriller","incorrect_answers":["Back in Black","Abbey Road","The Dark Side of the Moon"]},{"category":"Science & Nature","type":"multiple","difficulty":"easy","question":"How many planets make up our Solar System?","correct_answer":"8","incorrect_answers":["7","9","6"]},{"category":"Geography","type":"multiple","difficulty":"easy","question":"Which of the following Arab countries does NOT have a flag containing only Pan-Arab colours?","correct_answer":"Qatar","incorrect_answers":["Kuwait","United Arab Emirates","Jordan"]},{"category":"Science: Mathematics","type":"multiple","difficulty":"medium","question":"What is the alphanumeric representation of the imaginary number?","correct_answer":"i","incorrect_answers":["e","n","x"]},{"category":"Entertainment: Board Games","type":"multiple","difficulty":"hard","question":"In Magic: The Gathering, what was a tribute card to Jamie Wakefield&#039;s late wife Marilyn, who loved horses?","correct_answer":"Timbermare","incorrect_answers":["Loyal Pegasus","Vryn Wingmare","Sungrace Pegasus"]},{"category":"Science: Computers","type":"multiple","difficulty":"medium","question":"In programming, what do you call functions with the same name but different implementations?","correct_answer":"Overloading","incorrect_answers":["Overriding","Abstracting","Inheriting"]},{"category":"General Knowledge","type":"multiple","difficulty":"easy","question":"If you are caught &quot;Goldbricking&quot;, what are you doing wrong?","correct_answer":"Slacking","incorrect_answers":["Smoking","Stealing","Cheating"]},{"category":"Entertainment: Film","type":"multiple","difficulty":"medium","question":"Which of the following films was NOT directed by Quentin Tarantino?","correct_answer":"From Dusk till Dawn","incorrect_answers":["Jackie Brown","Pulp Fiction","Reservoir Dogs"]},{"category":"General Knowledge","type":"multiple","difficulty":"easy","question":"What is the famous Papa John&#039;s last name?","correct_answer":"Schnatter","incorrect_answers":["Chowder","Williams","ANDERSON"]},{"category":"Entertainment: Music","type":"multiple","difficulty":"easy","question":"Who is the lead singer of Arctic Monkeys?","correct_answer":"Alex Turner","incorrect_answers":["Jamie Cook","Matt Helders","Nick O&#039;Malley"]}]}
        """.trimIndent()
    }
}