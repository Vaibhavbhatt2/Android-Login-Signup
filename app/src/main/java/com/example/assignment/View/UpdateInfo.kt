package com.example.assignment.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.assignment.Database.myDatabase
import com.example.assignment.Models.DataObject
import com.example.assignment.Models.Entity
import com.example.assignment.R
import kotlinx.android.synthetic.main.activity_update_info.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateInfo : AppCompatActivity() {
    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_info)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()



        val pos=intent.getIntExtra("id",-1)

        if(pos!=-1){
            val title=DataObject.getData(pos).title

            val priority=DataObject.getData(pos).priority

            create_title.setText(title)
            create_priority.setText(priority)

            delete_button.setOnClickListener{
            DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos+1,title,priority))
                }
                Toast.makeText(this, "Task"+ title + "deleted successfully!", Toast.LENGTH_LONG).show()

                myIntent()
            }

            update_button.setOnClickListener{
                DataObject.updateData(pos,create_title.text.toString(),create_priority.text.toString())

                GlobalScope.launch {
                    database.dao().updateTask(Entity(pos+1,create_title.text.toString(),create_priority.text.toString()))
                }
//                Toast.makeText(this, "Task updated successfully!", Toast.LENGTH_LONG).show()

                myIntent()
            }
        }
    }

    fun myIntent(){
        val intent= Intent(this,MainActivity::class.java)
    startActivity(intent)
    }
}