package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.georlock.databinding.ActivityLoginBinding
import com.example.georlock.databinding.ActivityUserEditBinding
import java.net.URL

private lateinit var binding: ActivityUserEditBinding

class UserEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)
        binding = ActivityUserEditBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //뒤로가기 버튼
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        //유저의 정보를 넘겨받음
        var tmp: String? = ""
        var infos: List<String>? = null
        if (intent.hasExtra("infoss")) {
            tmp = intent.getStringExtra("infoss")
            infos = tmp?.split("@")
        }

        //수정 버튼 클릭시 변경된 비밀번호 값을 비교
        //수정된 내용 서버로 전송 후 UserMain Page로 전환
        binding.requestCkeck.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            if ("${binding.pwd1.text}".equals("${binding.pwd2.text}")) {
                Thread() {
                    var list: String =
                        onUserInfoUpdate("${infos?.get(2).toString()}", "${binding.pwd1.text}")
                    runOnUiThread {
                        Log.i("testLog", "loginedededed : ${infos?.get(2).toString()}")
                    }
                }.start()
                startActivity(intent)
            } else {
                Toast.makeText(this, "비밀번호 확인필요.", Toast.LENGTH_SHORT).show()
            }
        }

        //취소 버튼 클릭시 UserMain Page로 전환
        binding.cancel.setOnClickListener {
            val intent = Intent(this, UserMain::class.java)
            Log.i("testLog", "loginedededed : ${tmp}")
            intent.putExtra("infos", tmp)
            startActivity(intent)
        }
    }

    //사용자의 정보 수정
    fun onUserInfoUpdate(empNo: String, userPw: String): String {
        val url = URL("${Static.server_url}/userupdate?empNo=${empNo}&userPw=${userPw}")
        val txt = url.readText()
        return "${txt}"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}