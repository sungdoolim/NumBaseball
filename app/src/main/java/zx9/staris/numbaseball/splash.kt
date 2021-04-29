package zx9.staris.numbaseball

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val mHandler: Handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {  // 실행이 끝난후 확인 가능
            }
        }
        Handler().postDelayed(Runnable
        {
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            //  var a=wvnaver.
            // 실행할 동작 코딩
            mHandler.sendEmptyMessage(0) // 실행이 끝난후 알림
        }, 2100 // 2.1초후에 Home2로 이동합니다.
        )
    }
}