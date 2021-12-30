package com.example.patnerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var patnerAdapter: PatnerAdapter

    private val  btnAddPatnerListP: Button by lazy{ findViewById(R.id.btnAddPatnerList)}
    private val  btnDeletePatnerListP: Button by lazy{ findViewById(R.id.btnDeletePatnerList)}
    private val  etPatnerTitleP: EditText by lazy{ findViewById(R.id.etPatnerTitle)}
    private val  rvPatnerListP: RecyclerView by lazy{ findViewById(R.id.rvPatnerList)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        patnerAdapter=PatnerAdapter(mutableListOf())

        rvPatnerListP.adapter=patnerAdapter
        rvPatnerListP.layoutManager= LinearLayoutManager(this)

        btnAddPatnerListP.setOnClickListener {
            val patnerTitle=etPatnerTitleP.text.toString()
            if (patnerTitle.isNotEmpty()){
                val patner=Patner(patnerTitle)
                patnerAdapter.addPatner(patner)
                etPatnerTitleP.text.clear()
            }
        }

        btnDeletePatnerListP.setOnClickListener {
            patnerAdapter.deleteDonePatner()
        }
    }
}