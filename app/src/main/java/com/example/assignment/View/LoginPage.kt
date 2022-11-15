package com.example.assignment.View


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.Models.UserModel
import com.example.assignment.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*


class LoginPage : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var ref: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email=findViewById(R.id.login_email)
        password=findViewById(R.id.login_password)
        ref=getSharedPreferences("myapp", MODE_PRIVATE)



        Toast.makeText(this,"Connected", Toast.LENGTH_LONG).show()



        register_Btn.setOnClickListener{

            val registerScreenIntent= Intent(this, RegistrationActivity::class.java)
            startActivity(registerScreenIntent)




        }

        submit_login.setOnClickListener {

            val loginEmail = email.text.toString().trim()
            val loginPassword = password.text.toString().trim()

            val userJson = ref.getString(loginEmail, "")

            if(userJson==""){
                Toast.makeText(this, "User does not exist!", Toast.LENGTH_LONG).show()

            }else{
//                JSON TO user model
                val user=GsonBuilder().create().fromJson(userJson, UserModel::class.java)

                if(loginPassword.equals(user.password)){

                    ref.edit().putString("currentUser",loginEmail).apply()


                    Toast.makeText(this, "User Logged In!", Toast.LENGTH_LONG).show()


                    val mainScreenIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainScreenIntent)

                }else{
                    Toast.makeText(this, "password invalid!", Toast.LENGTH_LONG).show()

                }
            }

            }





        }

}


