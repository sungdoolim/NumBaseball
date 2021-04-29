package zx9.staris.numbaseball

import android.os.Bundle
import android.text.InputFilter.LengthFilter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ingame.*
import java.util.*

class ingame : AppCompatActivity() {

    val ran= Random()
    var boardList= arrayListOf<String>();
    var opor=0
    var trycount=0
    lateinit var correct:ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingame)
        val mode:Int=intent.getIntExtra("mode", 0)
       // Toast.makeText(this,mode.toString(),Toast.LENGTH_SHORT).show()
        val filters = arrayOf(
            LengthFilter(mode+2)
        )
        input.filters=filters
         correct=setModeGame(mode);
           // lv.adapter=ArrayAdapter <Any>(this, android.R.layout.simple_list_item_1, item)
        length.text="baseball length = "+(mode+2).toString()
        opor=mode*3+10
        input.hint="input "+(mode+2).toString()+" numbers"
        opportunity.text="You have "+opor.toString()+" Swing Chances!!"
      //  var str=r1.toString()+r2.toString()+r3.toString()+r4.toString()+r5.toString()
       // Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        when(mode){
            0->monster.setImageResource(R.drawable.snake)
            1->monster.setImageResource(R.drawable.gurung)

            2->monster.setImageResource(R.drawable.seahorse)

            else->monster.setImageResource(R.drawable.logo2)

        }
        board.adapter= ArrayAdapter<Any>(
            this, android.R.layout.simple_list_item_1,
            boardList as List<Any>
        )
        giveup.setOnClickListener(){
            Giveup()
        }

        trying.setOnClickListener(){
            //inputar , correct
            var instr=input.text.toString()
            if(instr.length!=mode+2){
                response.text="you must write "+(mode+2).toString()+" numbers"
                return@setOnClickListener
            }
            var inputar= arrayListOf<Int>()
            for(i in 0..instr.length-1){
                inputar.add(Integer.parseInt(instr[i].toString()))
            }
            if (inputar[0]==0){
                response.text="you can't write '0' first"
                return@setOnClickListener
            }
            for(i in 0..inputar.size-1){
                if(inputar[i] in inputar.subList(i+1,inputar.size)){
                    response.text="Don't write the same number"
                    return@setOnClickListener
                }
            }

            opor--
            trycount++
            if(opor<0){
                Giveup()
            }
            opportunity.text="You have "+opor.toString()+" Swing Chances!!"


            var countStrike=0
            var ball=0
           when(mode){
               0 ->
                   for (i in 0..1) {
                       if (inputar[i] == correct[i]) {
                           countStrike++
                       } else if (inputar[i] in correct) {
                           ball++
                       }
                   }


               1 -> for (i in 0..2) {
                   if (inputar[i] == correct[i]) {
                       countStrike++
                   } else if (inputar[i] in correct) {
                       ball++
                   }
               }
               2 -> for (i in 0..3) {
                   if (inputar[i] == correct[i]) {
                       countStrike++
                   } else if (inputar[i] in correct) {
                       ball++
                   }
               }
               else-> for (i in 0..4){
                   if (inputar[i]==correct[i]){
                       countStrike++
                   }else if (inputar[i] in correct){
                       ball++
                   }
               }

           }
            if(countStrike==mode+2){
                response.text="Perfect!!\nYou Win!!"
                Giveup()
            }
            else if(countStrike==0 && ball==0){
                response.text="OUT!!\nGreat!!"
            }else if(countStrike>=2){
                response.text="Great GoGo!!"
            }else if(countStrike+ball>=2){
                response.text="Good"
            }else{
                response.text="OK.. Don't give up!!"
            }

            boardList.add(inputar.toString()+" : "+countStrike.toString() + " strike , " + ball.toString() + " ball")
            (board.adapter as ArrayAdapter<Any>).notifyDataSetChanged()

        }






    }
    fun Giveup(){
        response.text="My Secret is.... : \n"+correct.toString()
        input.isEnabled=false
        trying.isEnabled=false
    }
    fun setModeGame(mode: Int): ArrayList<Int> {

        var num:Int;


       // var sorting:Queue<Int>;
        //sorting=LinkedList();
        var correct= arrayListOf<Int>()
        num=ran.nextInt(8);
        correct.add(num + 1)

        for(i in 0..mode){
            num=ran.nextInt(9);
            while(num in correct){
                num=ran.nextInt(9);
            }
            correct.add(num)
        }

        println(correct)
        return correct
    }
}