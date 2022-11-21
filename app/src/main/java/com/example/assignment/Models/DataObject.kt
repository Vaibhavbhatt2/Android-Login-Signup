package com.example.assignment.Models

import android.icu.text.CaseMap.Title
import com.example.assignment.View.CreateTask

object DataObject {

    var listdata= mutableListOf<ToDo>()

    fun setData(title:String,priority:String){
        listdata.add(ToDo(title,priority))

    }

    fun getAllData():List<ToDo>{
        return listdata
    }

    fun deleteAllData(){
        listdata.clear()
    }

    fun getData(pos:Int): ToDo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String){
        listdata[pos].title=title
        listdata[pos].priority=priority


    }
}