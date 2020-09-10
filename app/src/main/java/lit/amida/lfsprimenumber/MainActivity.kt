package lit.amida.lfsprimenumber

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object{
        private const val MAX_QUESTION = 10
    }

    val questions = IntArray(MAX_QUESTION)
    var rand = Random
    var point = 0
    var answerCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setQuestions()

        buttonF.setOnClickListener {
            if(!isPrimNum(questions[answerCount])) {
                Toast.makeText(applicationContext, "正解！", Toast.LENGTH_SHORT).show()
                point += 10
            }
            else Toast.makeText(applicationContext, "不正解！", Toast.LENGTH_SHORT).show()

            next()
        }

        buttonT.setOnClickListener {
            if(isPrimNum(questions[answerCount])) {
                Toast.makeText(applicationContext, "正解！", Toast.LENGTH_SHORT).show()
                point += 10
            }
            else Toast.makeText(applicationContext, "不正解！", Toast.LENGTH_SHORT).show()

            next()
        }


    }

    private fun isPrimNum(num: Int): Boolean{
        for(i in 2 until num){
            if(num % i == 0) {
                Log.d("isPrimNum", "$num divides by $i")
                return false
            }
        }

        Log.d("isPrimNum", "$num is Prime Number")
        return true
    }

    private fun next(){
        answerCount++

        if(answerCount >= MAX_QUESTION){
            textScore.text = "Score:${point}点！！"
            textScore.textSize = 24f
            setQuestions()
        } else{
            textView.text = "${questions[answerCount]}"
            textScore.text = "Score:${point}点"
            textScore.textSize = 14f
        }
    }

    private fun setQuestions(){
        for(i in 0 until MAX_QUESTION){
            val number = rand.nextInt(2, 100)
            Log.d("Num", "$number")
            questions[i] = number
        }
        point = 0
        answerCount = 0

        textView.text = "${questions[answerCount]}"
    }


}