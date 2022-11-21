package com.example.assignment.View

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.assignment.Database.myDatabase
import com.example.assignment.Models.DataObject
import com.example.assignment.Models.UserModel
import com.example.assignment.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var email:TextView

    private lateinit var ref: SharedPreferences

    private lateinit var database: myDatabase





    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        //on clicking on floating circular button,the user will be navigated to creeate task screen
        add.setOnClickListener {
    val intent=Intent(this,CreateTask::class.java)
            startActivity(intent)
        }

    setRecycler()

        deleteAll.setOnClickListener{
        DataObject.deleteAllData()
            GlobalScope.launch {
                database.dao().deleteAll()



            }
            Toast.makeText(this,"All tasks deleted successfully", Toast.LENGTH_LONG).show()

            setRecycler()
        }

//        name=findViewById(R.id.displayName)
//        email=findViewById(R.id.displayEmail)
        ref=getSharedPreferences("myapp", MODE_PRIVATE)


        val currentUserEmail=ref.getString("currentUser","")

        val userJson = ref.getString(currentUserEmail, "")

        val user= GsonBuilder().create().fromJson(userJson, UserModel::class.java)



//        name.setText(user.name)
//        email.setText(user.email)

//        logout.setOnClickListener{
//            removeUser()
//            val loginIntent= Intent(this, LoginPage::class.java)
//            Toast.makeText(this,"Logged out Successfully!", Toast.LENGTH_LONG).show()
//
//            startActivity(loginIntent)
//        }


    }

    private fun removeUser(){
        ref.edit().remove("currentUser").commit()
    }

    private fun setRecycler(){
        recycler_view.adapter=Adapter(DataObject.getAllData())
        recycler_view.layoutManager=LinearLayoutManager(this)

    }
}