package com.example.assignment.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.Models.Dao
import com.example.assignment.Models.Entity

@Database(entities = [Entity::class], version = 1)
abstract class myDatabase:RoomDatabase() {
    abstract fun dao():Dao



}