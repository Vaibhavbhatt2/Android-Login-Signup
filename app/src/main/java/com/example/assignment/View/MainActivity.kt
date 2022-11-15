package com.example.assignment.View

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.assignment.Models.UserModel
import com.example.assignment.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var email:TextView

    private lateinit var ref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name=findViewById(R.id.displayName)
        email=findViewById(R.id.displayEmail)
        ref=getSharedPreferences("myapp", MODE_PRIVATE)


        val currentUserEmail=ref.getString("currentUser","")

        val userJson = ref.getString(currentUserEmail, "")

        val user= GsonBuilder().create().fromJson(userJson, UserModel::class.java)



        name.setText(user.name)
        email.setText(user.email)

        logout.setOnClickListener{
            removeUser()
            val loginIntent= Intent(this, LoginPage::class.java)
            Toast.makeText(this,"Logged out Successfully!", Toast.LENGTH_LONG).show()

            startActivity(loginIntent)
        }


    }

    private fun removeUser(){
        ref.edit().remove("currentUser").commit()
    }
}