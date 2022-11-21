package com.example.assignment.View

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.room.Room
import com.example.assignment.Database.myDatabase
import com.example.assignment.Models.DataObject
import com.example.assignment.Models.ToDo
import com.example.assignment.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private lateinit var database: myDatabase



    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 3000
    private lateinit var ref: SharedPreferences

    private val mRunnable: Runnable = Runnable {
        ref=getSharedPreferences("myapp", MODE_PRIVATE)

        if (ref.contains("currentUser")) {
            val mainScreenIntent =  Intent(this, MainActivity::class.java)
            startActivity(mainScreenIntent)
        } else {
            val loginScreenIntent =  Intent(this, LoginPage::class.java)
            startActivity(loginScreenIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        GlobalScope.launch {
            DataObject.listdata=database.dao().getTasks() as MutableList<ToDo>
        }
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, splashDelay)


    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}