package com.example.assignment.View

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.Models.UserModel
import com.example.assignment.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var ref: SharedPreferences
    private lateinit var name:EditText
    private lateinit var password:EditText
    private lateinit var email:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        name=findViewById(R.id.name)
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        ref=getSharedPreferences("myapp", MODE_PRIVATE)


        login_button.setOnClickListener{
            val loginIntent= Intent(this, LoginPage::class.java)
            startActivity(loginIntent)

        }




        register_button.setOnClickListener {
           register()







        }


    }

    private fun register(){
        val mName=name.text.toString()
        val mEmail=email.text.toString()
        val mPassword=password.text.toString()

        if(mName==""||mEmail==""||mPassword==""){
            Toast.makeText(this,"Enter Valid Credentials", Toast.LENGTH_LONG).show()

        }else{
            val user= UserModel(name=mName,email=mEmail,password=mPassword)

//        Usermodel to Json
            val userJson=Gson().toJson(user)
            ref.edit().putString(mEmail,userJson).apply()
            Toast.makeText(this,"User Registered Successfully!", Toast.LENGTH_LONG).show()

            val loginIntent= Intent(this, LoginPage::class.java)
            startActivity(loginIntent)
        }









    }


}