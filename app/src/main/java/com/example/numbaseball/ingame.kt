package com.example.numbaseball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ingame.*
import java.util.*
import kotlin.collections.ArrayList

class ingame : AppCompatActivity() {

    val ran= Random()
    var boardList= arrayListOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingame)
        val mode:Int=intent.getIntExtra("mode",0)
       // Toast.makeText(this,mode.toString(),Toast.LENGTH_SHORT).show()
        var correct=setModeGame(mode);
           // lv.adapter=ArrayAdapter <Any>(this, android.R.layout.simple_list_item_1, item)

      //  var str=r1.toString()+r2.toString()+r3.toString()+r4.toString()+r5.toString()
       // Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        board.adapter= ArrayAdapter <Any>(this, android.R.layout.simple_list_item_1,
            boardList as List<Any>
        )

        trying.setOnClickListener(){
            //inputar , correct
            var instr=input.text.toString()
            var inputar= arrayListOf<Int>()
            for(i in 0..instr.length-1){
                inputar.add(Integer.parseInt(instr[i].toString()))
            }
            var countStrike=0
            var ball=0
           when(mode){
               0-> for (i in 0..1){
                    if (inputar[i]==correct[i]){
                        countStrike++
                    }else if (inputar[i] in correct){
                        ball++
                    }
               }


               1-> for (i in 0..2){
                   if (inputar[i]==correct[i]){
                       countStrike++
                   }else if (inputar[i] in correct){
                       ball++
                   }
               }
               2-> for (i in 0..3){
                   if (inputar[i]==correct[i]){
                       countStrike++
                   }else if (inputar[i] in correct){
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
            boardList.add(countStrike.toString()+" strike"+ball.toString()+" ball")
            (board.adapter as ArrayAdapter<Any>).notifyDataSetChanged()
            println(countStrike.toString()+" strike"+ball.toString()+"ball  ")
            Toast.makeText(this,countStrike.toString()+" strike"+ball.toString()+"ball  ",Toast.LENGTH_SHORT).show()
        }






    }
    fun setModeGame(mode:Int): ArrayList<Int> {

        var num:Int;


       // var sorting:Queue<Int>;
        //sorting=LinkedList();
        var correct= arrayListOf<Int>()
        num=ran.nextInt(8);
        correct.add(num+1)

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