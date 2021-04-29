package zx9.staris.numbaseball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent= Intent(this, ingame::class.java)

        easy.setOnClickListener(){
            intent.putExtra("mode",0)
            startActivity(intent)
        }
        normal.setOnClickListener(){

            intent.putExtra("mode",1)
            startActivity(intent)
        }
        hard.setOnClickListener(){

            intent.putExtra("mode",2)
            startActivity(intent)
        }
        crazy.setOnClickListener(){

            intent.putExtra("mode",3)
            startActivity(intent)
        }









    }
}