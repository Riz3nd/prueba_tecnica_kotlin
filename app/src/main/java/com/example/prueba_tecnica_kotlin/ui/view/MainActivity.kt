package com.example.prueba_tecnica_kotlin.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView;
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_tecnica_kotlin.R
import com.example.prueba_tecnica_kotlin.databinding.ActivityMainBinding
import com.example.prueba_tecnica_kotlin.ui.viewmodel.UserViewModel
import com.example.prueba_tecnica_kotlin.utils.Utils.Companion.isOnline

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var recycler: RecyclerView
    lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()

    }

    private fun initComponents() {
        supportActionBar?.show()
        supportActionBar?.setTitle(R.string.user_list)
        recycler = binding.recyclerUsers
        recycler.layoutManager = LinearLayoutManager(this)
        if (isOnline(this)){
            userViewModel.onCreate()
            userViewModel.userModel.observe(this, Observer {
                if(!it.isJsonNull){
                    adapter = UsersAdapter(this, it.asJsonArray)
                    recycler.adapter = adapter
                }
            })
            binding.animation.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.animation.visibility = View.VISIBLE
        }

        userViewModel.isLoading.observe(this, Observer {
            binding.progressBar.isVisible = it
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        try {
            menuInflater.inflate(R.menu.menu_search, menu)
            val searchItem = menu.findItem(R.id.action_search)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    filterUsers(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    filterUsers(newText)
                    return false
                }
            })
        } catch(e: Exception){ e.printStackTrace() }
        return true
    }

    fun filterUsers(data:String){
        try {
            adapter.filter(data)
        } catch (e:Exception){ e.printStackTrace()}
    }
}