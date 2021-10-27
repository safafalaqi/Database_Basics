package com.example.databasebasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etLocation: EditText
    lateinit var btSave: Button
    lateinit var etquery: EditText
    lateinit var btRetrive: Button
    lateinit var tvDisplay: TextView
    val databaseHelper by lazy { DBHlpr(applicationContext) }

    lateinit var etNameUpdate: EditText
    lateinit var etLocUpdate: EditText
    lateinit var btUpdate: Button
    lateinit var etDelete: EditText
    lateinit var btDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etLocation = findViewById(R.id.etLocation)
        btSave = findViewById(R.id.btSave)
        btSave.setOnClickListener {
            //ADD DATA
            val name = etName.text.toString()
            val location = etLocation.text.toString()
            if (name.isNotBlank() && location.isNotBlank()) {

                var status = databaseHelper.saveData(name, location)
                Toast.makeText(
                    applicationContext,
                    "data saved successfully! " + status,
                    Toast.LENGTH_SHORT
                ).show()
                clearText()
            } else
                Toast.makeText(applicationContext, "Can not be empty ", Toast.LENGTH_SHORT).show()

        }
        //RETRIEVE DATA
        etquery = findViewById(R.id.etQuery)
        btRetrive = findViewById(R.id.btRetrive)
        tvDisplay = findViewById(R.id.tvDisplay)
        btRetrive.setOnClickListener {
            val name = etquery.text.toString()
            if (name.isNotBlank()) {
                var location = databaseHelper.retrieveData(name)
                tvDisplay.text = location
                clearText()
            }else
                Toast.makeText(applicationContext, "Can not be empty ", Toast.LENGTH_SHORT).show()
        }
        //UPDATE DATA
        etNameUpdate = findViewById(R.id.etNameUpdate)
        etLocUpdate = findViewById(R.id.etLocationUpdate)
        btUpdate = findViewById(R.id.btUpdate)
        btUpdate.setOnClickListener {
            if (etNameUpdate.text.isNotBlank() && etLocUpdate.text.isNotBlank()) {
                var status = databaseHelper.updateLocation(
                    etNameUpdate.text.toString(),
                    etLocUpdate.text.toString()
                )
                clearText()
            } else
                Toast.makeText(applicationContext, "Can not be empty ", Toast.LENGTH_SHORT).show()
        }

        //DELETE DATA
        etDelete = findViewById(R.id.etNameDel)
        btDelete = findViewById(R.id.btDel)
        btDelete.setOnClickListener {
            if (etDelete.text.isNotBlank()) {
                databaseHelper.deleteName(etDelete.text.toString())
                clearText()
            } else
                Toast.makeText(applicationContext, "Can not be empty ", Toast.LENGTH_SHORT).show()

        }
    }


    fun clearText(){
        etName.text.clear()
        etLocation.text.clear()
        etquery.text.clear()
        etNameUpdate.text.clear()
        etLocUpdate.text.clear()
    }


}