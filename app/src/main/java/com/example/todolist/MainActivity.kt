package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val client = OkHttpClient()
        val request = Request.Builder().url("https://jsonplaceholder.typicode.com/todos").build()

        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
            }
            override fun onResponse(call: Call, response: Response) {
                val jsonArray = JSONArray(response.body!!.string())

                val myListView = binding.listView
                val titleArray = ArrayList<String>()
                val doneArray = ArrayList<Boolean>()

                for(i:Int in 1 until jsonArray.length()){
                    titleArray.add(jsonArray.getJSONObject(i).getString("title"))
                    doneArray.add(jsonArray.getJSONObject(i).getBoolean("completed"))
                }

                runOnUiThread {
                    val adapter = MyListAdapter(this@MainActivity, titleArray, doneArray)

                    myListView.setAdapter(adapter)
                }
            }
        })
    }
}