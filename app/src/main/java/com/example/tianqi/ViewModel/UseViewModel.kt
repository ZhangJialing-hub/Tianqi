package com.example.tianqi.ViewModel

/*
* description:  ToDo:ViewModel部分
 * author:zjl
 * email:3507386031@qq.com
 * date:2026/5/20 9：10
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.example.tianqi.Model.Comfort
import com.example.tianqi.Model.GsonData
import com.example.tianqi.Model.Realtime
import com.example.tianqi.Repository.NetRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UseViewModel: ViewModel(){
    private val _realtime= MutableStateFlow(Realtime(0.0,"未知",0.0, Comfort(0,"未知")))
    val realtime: StateFlow<Realtime>
        get() = _realtime

    private val _temperature= MutableStateFlow(0.0)
    val temperature: StateFlow<Double>
        get() = _temperature

    private val _apparentTemperature=MutableStateFlow(0.0)
    val apparentTemperature: StateFlow<Double>
        get() = _apparentTemperature


    private val _skycon= MutableStateFlow("")
    val skycon: StateFlow<String>
        get() = _skycon

    private val _comfort= MutableStateFlow(Comfort(0, ""))
    val comfort: StateFlow<Comfort>
        get() = _comfort

    private val _index= MutableStateFlow(0)
    val index: StateFlow<Int>
        get() = _index

    private val _desc= MutableStateFlow("")
    val desc: StateFlow<String>
        get() = _desc

    private val _loading=MutableStateFlow(false)
    val loading:StateFlow<Boolean>
        get() = _loading

    fun fetchData()
    {
        viewModelScope.launch {
            _loading.value=true
            try {
                val realtime= NetRepository.apiService.getData().result.realtime
                Log.d("UseViewModel","realtime,$realtime")
                _realtime.value=realtime
                _desc.value=realtime.comfort?.desc?:"未知"
                _index.value=realtime.comfort?.index?:0
                _comfort.value=realtime.comfort?: Comfort(0,"未知")
                _skycon.value=realtime.skycon
                _temperature.value=realtime.temperature
                _apparentTemperature.value=realtime.apparent_temperature

            }
            catch (e: Exception)
            {
                Log.e("UseViewModel","请求失败")
                    e.printStackTrace()

            }
            finally {
                _loading.value=false

            }

        }

    }
}