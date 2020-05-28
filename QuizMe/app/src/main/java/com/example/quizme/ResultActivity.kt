package com.example.quizme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvUsername.text = intent.getStringExtra(Constants.USER_NAME)
        tvScore.text =
            "${intent.getStringExtra(Constants.CORRECT_ANSWERS)} out of ${intent.getStringExtra(Constants.TOTAL_QUESTIONS)}"

        btnFinish.setOnClickListener{
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
