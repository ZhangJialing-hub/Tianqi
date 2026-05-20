package com.example.tianqi.Model

/*
* description:  ToDo:存放彩云api包含的数据
 * author:zjl
 * email:3507386031@qq.com
 * date:2026/5/17 13：38
 */
data class GsonData(
    val result: Result
)
data class Result(
    val realtime:Realtime
)
data class Realtime(
    val temperature: Double,
    val skycon: String,
    val apparent_temperature: Double,
    val comfort:Comfort?
)
data class Comfort(
    val index: Int,
    val desc: String
)