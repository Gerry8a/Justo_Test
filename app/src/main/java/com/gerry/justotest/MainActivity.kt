package com.gerry.justotest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gerry.justotest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navContoller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavgation()
        supportActionBar!!.hide()
    }

    private fun setUpNavgation() {
        navContoller = findNavController(R.id.navHostFragment)
        binding.bottomNavigation.setupWithNavController(navContoller)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_user -> {
                    navContoller.popBackStack()
                    navContoller.navigate(R.id.informationFragment)
                    true
                }
                else -> false
            }
        }
    }
}