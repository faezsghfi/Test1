package com.basalam.test1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.basalam.test1.adapter.DataAdapter
import com.basalam.test1.databinding.ActivityMainBinding
import com.basalam.test1.util.ApiState
import com.basalam.test1.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataAdapter: DataAdapter
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()

        mainViewModel.getItem()

        Log.e("faeze","befor")

        lifecycleScope.launchWhenStarted {
            mainViewModel._itemStateFlow.collect {it->
                when(it){
                    is ApiState.Loading->{
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                        Log.e("faeze","Loading")
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.e("faeze", "Failure:"+it.msg)
                    }
                    is ApiState.Success->{

                        Log.d("faeze", "Success")
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        dataAdapter.setData(it.data)
                        dataAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{
                    }
                }
            }
        }

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