package com.example.quizme

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var myCurrentPosition: Int = 1
    private var myQuestionsList: ArrayList<Question>? = null
    private var mySelectedOptionPosition: Int = 0
    private var myQuizScore: Int = 0
    private var myUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        myUserName = intent.getStringExtra(Constants.USER_NAME)
        myQuestionsList = Constants.getQuestions()
        setQuestion()
        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion() {
        val question = myQuestionsList!![myCurrentPosition - 1]

        defaultOptionsView()

        if(myCurrentPosition == myQuestionsList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = myCurrentPosition
        tvProgress.text = "$myCurrentPosition" + "/" + progressBar.max

        tvQuestion.text = question!!.question
        ivImage.setImageResource(question.img)
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.optionOne -> {
                selectedOptionView(optionOne, 1)
            }
            R.id.optionTwo -> {
                selectedOptionView(optionTwo, 2)
            }
            R.id.optionThree -> {
                selectedOptionView(optionThree, 3)
            }
            R.id.optionFour -> {
                selectedOptionView(optionFour, 4)
            }
            R.id.btnSubmit -> {
                if (mySelectedOptionPosition == 0) {
                    myCurrentPosition++
                    when {
                        myCurrentPosition <= myQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent: Intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, myUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, myQuizScore.toString())
                            intent.putExtra(Constants.TOTAL_QUESTIONS, myQuestionsList!!.size.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = myQuestionsList?.get(myCurrentPosition-1)
                    if(question!!.correctAnswer != mySelectedOptionPosition) {
                        answerView(mySelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        myQuizScore++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(myCurrentPosition == myQuestionsList!!.size) {
                        btnSubmit.text = "FINISH"
                    } else {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mySelectedOptionPosition = 0
                }

            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }
}
