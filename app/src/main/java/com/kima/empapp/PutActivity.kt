package com.kima.empapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class PutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_put)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val idedittext =findViewById<EditText>(R.id.empid)
        val nameedittext = findViewById<EditText>(R.id.empname)
        val btnupdate =findViewById<Button>(R.id.btnupdate)
        btnupdate.setOnClickListener {
            val helper =  ApiHelper(applicationContext)
            val api ="https://susan99.pythonanywhere.com/employees"
            val body = JSONObject()
            body.put("id",idedittext.text.toString())
            body.put("firstname",nameedittext.text.toString())
            helper.put(api,body)
        }
    }
}