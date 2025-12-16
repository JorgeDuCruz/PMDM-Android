package com.dam.pruebasroomsqlite.Model

import android.app.Application
import android.util.Log
import androidx.room.Room

class Controller(applicationContext: Application) {
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).allowMainThreadQueries().build()
    val userDao = db.userDao()
    fun getAll(): List<User>{
        val users = userDao.getAll()
        Log.d("SQLiteProbas","Hay $users")
        return users
    }

    fun insertUser(user: User){
        userDao.insertAll(user)
    }
}