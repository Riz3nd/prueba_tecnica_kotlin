package com.example.prueba_tecnica_kotlin.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.prueba_tecnica_kotlin.R
import com.example.prueba_tecnica_kotlin.databinding.ActivityMainBinding
import com.example.prueba_tecnica_kotlin.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.onCreate()
        initComponents()

        userViewModel.userModel.observe(this, Observer {
            recycler.adapter = UsersAdapter(this, it.asJsonArray)
        })
    }

    private fun initComponents(){
        recycler = binding.recyclerUsers
        recycler.layoutManager = LinearLayoutManager(this)
    }
}