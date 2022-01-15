package com.bignerdranch.android.geoquiz

import QuizViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val KEY_INDEX = "index"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var backButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {

            checkAnswer(true)
        }

        falseButton.setOnClickListener {

            checkAnswer(false)
        }

        nextButton.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestion()
        }

        backButton.setOnClickListener {

            quizViewModel.moveToBack()
            updateQuestion()
        }

        questionTextView.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {

        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {

        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {

        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {

        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {

        val questionTextResId = quizViewModel.currentQuestionText

        if (quizViewModel.answered()) {
            disableButton()
        } else
            enableButton()

        questionTextView.setText(questionTextResId)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId: Int

        if (userAnswer == correctAnswer) {

            messageResId = R.string.correct_toast
            quizViewModel.pressButton(true)
        } else {

            messageResId = R.string.incorrect_toast
            quizViewModel.pressButton(false)
        }
        disableButton()
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun disableButton() {

        falseButton.isEnabled = false
        trueButton.isEnabled = false
    }

    private fun enableButton() {

        falseButton.isEnabled = true
        trueButton.isEnabled = true
    }

    /*  private fun numberSuccess() {


          var numberSuccess = 0
          for (i in amountQuestion) {
              if (i == 1)
                  numberSuccess += 1
          }

          Toast.makeText(this, "Number of successful responses $numberSuccess", Toast.LENGTH_SHORT)
              .show()

      }*/
}

