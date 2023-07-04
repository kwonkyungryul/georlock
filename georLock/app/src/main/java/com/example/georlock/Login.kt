package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.georlock.Static.Companion.server_url
import com.example.georlock.databinding.ActivityLoginBinding
import java.net.URL

private lateinit var binding: ActivityLoginBinding

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var empNo = MyApplication.prefs.getString("empNo", "no")
        var userPw = MyApplication.prefs.getString("userPw", "no")
        var temp: String = ""
        if (intent.hasExtra("token")) {
            temp = intent.getStringExtra("token").toString()
        }
        if (empNo != "no" && userPw != "no") {
            onLogin(empNo, userPw, "${temp}")
        }

        // 로그인 버튼 클릭시 로그인 입력 값 서버와 비교 후 로그인 성공 or 실패
        // 로그인 성공시 MainActivity 페이지로 전환함
        binding.loginButton.setOnClickListener {
            onLogin("${binding.username.text}", "${binding.password.text}", "${temp}")
        }
    }


    fun onLogin(empNo: String, userPw: String, tokens: String) {
        Log.i("testLog", "checkBox u:${binding.checkBox.isChecked}")
        var tmp: String = ""
        Log.i("testLog", "loginclick u:${binding.username.text}p:${binding.password.text}")
        val LOGIN_FAILED_RESPONS = "0실패"
        val LOGIN_SUCCESS_ADMIN_RESPONS = "1성공"  //관리자 로그인 성공
        val LOGIN_SUCCESS_USER_RESPONS = "2성공"   //일반사용자 로그인 성공

        Thread() {
            tmp = checkLogin(empNo, userPw, tokens)
            var tmps: List<String> = tmp.split("@");
            runOnUiThread {
                Log.i("testLog", "loginclick : ${tmp}")
                if ("${tmps[0]}".equals(LOGIN_FAILED_RESPONS)) {
                    Log.i("testLog", "로그인실패")
                    Toast.makeText(this, "로그인 실패.", Toast.LENGTH_SHORT).show()
                } else if ("${tmps[0]}".equals(LOGIN_SUCCESS_ADMIN_RESPONS)) {
                    Log.i("testLog", "로그인성공")
                    val intent = Intent(this, MainActivity::class.java)
                    if (binding.checkBox.isChecked) {
                        // 데이터 저장
                        MyApplication.prefs.setString("empNo", "${binding.username.text}")
                        MyApplication.prefs.setString("userPw", "${binding.password.text}")
                    }
                    intent.putExtra("infos", "${tmps[3]}")
                    startActivity(intent)
                } else if ("${tmps[0]}".equals(LOGIN_SUCCESS_USER_RESPONS)) {
                    Log.i("testLog", "로그인성공1 +${tmps}")
                    if (binding.checkBox.isChecked) {
                        // 데이터 저장
                        MyApplication.prefs.setString("empNo", "${binding.username.text}")
                        MyApplication.prefs.setString("userPw", "${binding.password.text}")
                    }
                    val intent = Intent(this, UserMain::class.java)
                    intent.putExtra("infos", "${tmps[1]}@${tmps[2]}@${tmps[3]}")
                    startActivity(intent)
                }
            }
        }.start()
    }

    fun checkLogin(empNo: String, userPw: String, tokens: String): String {
        val url = URL("${server_url}/login?empNo=${empNo}&userPw=${userPw}&tokens=${tokens}")
        val txt = url.readText()
        return "${txt}"
    }

}

