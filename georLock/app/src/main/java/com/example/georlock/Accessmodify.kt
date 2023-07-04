package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.MenuItem
import com.example.georlock.databinding.ActivityInfoBinding  // 추가된 import
import java.net.HttpURLConnection
import java.net.URL

class Accessmodify : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding  // View Binding instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)  // initialising binding
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // 페이지 전환시 전 페이지의 정보를 넘겨 받는 코드 (발표할 필요없는 코드)
        var strings: String? = intent.getStringExtra("info")?.replace("\n", "")
        var infos: List<String>? = strings?.split(" ")
        Log.i("testLog", "${infos?.size}")

        if (intent.hasExtra("info")) {
            Log.i("testLog", intent.getStringExtra("info").toString())
            binding.empNum.setText(infos?.get(2))
            binding.username.setText(infos?.get(5))
            binding.intime.setText(infos?.get(8))
            binding.outtime.setText(infos?.get(10))
        }

        // 삭제버튼 클릭시 회원정보 삭제 후 출입권한 페이지로 전환됨
        binding.buttonDelete.setOnClickListener {
            Log.i("testLog", "loginclick u:${binding.username.text}")
            Thread() {
                onDeleteAccess("${infos?.get(2)}")
                runOnUiThread {
                    val intent = Intent(this, AuthorizationList::class.java)
                    startActivity(intent)
                }
            }.start()
        }

        // 수정버튼 클릭시 수정된 내용을 저장한 후 출입권한 페이지로 전환됨
        binding.buttonUpdate.setOnClickListener {
            Log.i("testLog", "loginclick u:${binding.username.text}")
            Thread() {
                onUpdateAccess("${binding.empNum.text}", "${binding.intime.text}", "${binding.outtime.text}")
                runOnUiThread {
                    val intent = Intent(this, AuthorizationList::class.java)
                    startActivity(intent)
                }
            }.start()
        }
    }

    // 회원정보 삭제
    fun onDeleteAccess(empNo: String) {
        val url = URL("${Static.server_url}/delete?empNo=${empNo}")
        val conn = url.openConnection() as HttpURLConnection // casting
        val txt = url.readText()
    }

    // 회원정보 수정
    fun onUpdateAccess(empNo: String, intime: String, outtime: String) {
        val url = URL("${Static.server_url}/update?empNo=${empNo}&intime=${intime}&outtime=${outtime}")
        val txt = url.readText()
    }

    // toolbar의 back키 눌렀을 때 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
