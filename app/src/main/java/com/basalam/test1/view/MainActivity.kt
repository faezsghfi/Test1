package com.basalam.test1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.basalam.test1.adapter.DataAdapter
import com.basalam.test1.databinding.ActivityMainBinding
import com.basalam.test1.repository.MainRepository.resultList
import com.basalam.test1.util.TypefaceUtil
import com.basalam.test1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataAdapter: DataAdapter
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.progressBar.isVisible = true
        binding.recyclerview.isVisible = false
        mainViewModel.getItem().observe(this, {resultList->
            dataAdapter.setData(resultList)
            dataAdapter.notifyDataSetChanged()

            binding.progressBar.isVisible = false
            binding.recyclerview.isVisible = true
    })
    }

    private fun initRecyclerview() {
        dataAdapter= DataAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=dataAdapter
        }
    }
}