package com.example.assignment.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.assignment.Database.myDatabase
import com.example.assignment.Models.DataObject
import com.example.assignment.Models.Entity
import com.example.assignment.R
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateTask : AppCompatActivity() {
    private lateinit var database:myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        save_button.setOnClickListener{
            //check for empty title and priority field
            if(create_title.text.toString().trim{it<=' '}.isNotEmpty()
                && create_priority.text.toString().trim{it<=' '}.isNotEmpty()){

                var title=create_title.text.toString()
                var priority=create_priority.text.toString()

                DataObject.setData(title,priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,priority))
                    Log.i("vaibhav",database.dao().getTasks().toString())

                }
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)

            }
        }

    }
}