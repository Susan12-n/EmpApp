package com.kima.empapp

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject

class GetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        fetch the progressbar and the textview
        val progressbar =findViewById<ProgressBar>(R.id.progress)
        val empdata = findViewById<TextView>(R.id.empdata)
//        get our base url/base endpoint
        val api ="https://susan99.pythonanywhere.com/employees"
//        get instance of Api helper
//        meaning we create an object of Api helper
        val helper =  ApiHelper(applicationContext)
        helper.get(api,object:ApiHelper.CallBack{
            override fun onSuccess(result: String?) {
//                convert to json array
                val employeejsonarray = JSONArray(result.toString())
//                Toast.makeText(applicationContext, "$employeejsonarray", Toast.LENGTH_SHORT).show()
//                loop through the data
                (0 until employeejsonarray.length()).forEach {
                    val singleemployee = employeejsonarray.getJSONObject(it)
//                    Toast.makeText(applicationContext, "$singleemployee", Toast.LENGTH_SHORT).show()
//                    display user id in a textview
                    empdata.append("employee ID" + singleemployee.get("id") + "\n \n")
                    empdata.append("employee firstname" + singleemployee.get("firstname") + "\n \n")
                    empdata.append("employee others" + singleemployee.get("others") + "\n \n")
                    empdata.append("employee salary" + singleemployee.get("salary") + "\n \n")
                    empdata.append("employee department" + singleemployee.get("department") + "\n \n")
                    empdata.append("\n \n \n")



                }
//                stop the progress bar from loading whe there is results
               progressbar.visibility =View.GONE



            }

        })
    }
}