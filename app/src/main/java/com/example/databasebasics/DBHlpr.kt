package com.example.databasebasics

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class DBHlpr(val context: Context):SQLiteOpenHelper(context,"details.db",null,1) {
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
      if(db!=null){
          db.execSQL("CREATE TABLE students (Name text, Location text)")
      }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }
    fun saveData(name:String, location:String): Long {
     val cv=ContentValues()
        cv.put("Name",name)
        cv.put("Location",location)
       var status =  sqLiteDatabase.insert("students",null,cv)
        return status
    }

    fun retrieveData(name: String): String {
       try {
            var cursor: Cursor =
                sqLiteDatabase.query(
                    "students", null, "Name=?",
                    arrayOf(name), null, null, null
                )
            cursor.moveToFirst()
            var location = cursor.getString((cursor.getColumnIndex("Location")))
           return location
        }catch(e:Exception) {
               return "not found"
       }
    }

    fun updateLocation(name: String, location: String){

        try {
            val cv=ContentValues()
            cv.put("Location",location)
            //UPDATE students SET Location=? WHERE Name?
            var status= sqLiteDatabase.update("students",cv,"Name = ?",arrayOf(name))
            Toast.makeText(context, "Update success! $status", Toast.LENGTH_SHORT)
                .show()

        }catch (e:Exception){
            Toast.makeText(context,"Can not Update! ",  Toast.LENGTH_SHORT  ).show()

        }
    }

    fun deleteName(name: String) {
        try {
            sqLiteDatabase.delete("students","Name = ?",arrayOf(name))
            Toast.makeText(context,"Delete success! ",  Toast.LENGTH_SHORT  ).show()
        }catch (e:Exception){
            Toast.makeText(context,"Can not delete! ",  Toast.LENGTH_SHORT  ).show()
        }

    }

}