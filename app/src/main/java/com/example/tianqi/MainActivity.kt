package com.example.tianqi

import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tianqi.ViewModel.UseViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UseViewModel
    private lateinit var btnFetchData: Button
    private lateinit var tvSkycon: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvApparentTemperature: TextView
    private lateinit var tvdesc : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel= ViewModelProvider(this)[UseViewModel::class.java]
        tvSkycon=findViewById<TextView>(R.id.tv_skycon)
        tvdesc=findViewById<TextView>(R.id.tv_desc)
        tvTemperature=findViewById<TextView>(R.id.tv_temperature)
        tvApparentTemperature=findViewById<TextView>(R.id.tv_apparent_temperature)
        btnFetchData=findViewById<Button>(R.id.btn)

        initClick()
        update()

        }
    private fun initClick(){
        btnFetchData.setOnClickListener {
            viewModel.fetchData()
        }
    }

    private fun update() {
        lifecycleScope.launch {
            Log.d("lifecycleScopeerror","有更新")
            launch {
                viewModel.skycon.collect {
                    tvSkycon.text = "天气：$it"
                }
            }
            launch {
                viewModel.temperature.collect {
                    tvTemperature.text = "温度：$it"
                }
            }
            launch {
                viewModel.apparentTemperature.collect {
                    tvApparentTemperature.text = "体感温度：$it"
                }
            }
            launch {
                viewModel.desc.collect {
                    tvdesc.text = "舒适度：$it"
                }
            }
        }
    }

    }
