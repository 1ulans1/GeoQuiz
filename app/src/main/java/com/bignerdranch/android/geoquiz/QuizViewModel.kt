import androidx.lifecycle.ViewModel
import com.bignerdranch.android.geoquiz.Question
import com.bignerdranch.android.geoquiz.R

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private val amountQuestion: IntArray = IntArray(questionBank.size) { 0 }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {

        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToBack() {

        currentIndex = if (currentIndex - 1 < 0)
            questionBank.size - 1
        else
            currentIndex - 1
    }

    fun pressButton(answer: Boolean) {

        if (answer)
            amountQuestion[currentIndex] = 1
        else
            amountQuestion[currentIndex] = -1
    }

    fun answered() = amountQuestion[currentIndex] != 0
}
