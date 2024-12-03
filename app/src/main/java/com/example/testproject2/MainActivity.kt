package com.example.testproject2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject2.api.ApiInterface
import com.example.testproject2.api.ApiUtilities
import com.example.testproject2.model.Jokes
import com.example.testproject2.model.adapterModel
import com.example.tetsprep.adapter.JokesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rcv : RecyclerView
    private lateinit var adapter : JokesAdapter
    private lateinit var list : ArrayList<adapterModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = ArrayList()
        rcv = findViewById(R.id.rcv)
        adapter = JokesAdapter(this,list)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)



        GlobalScope.launch {
            val response = apiInterface.getData()

            val jokesResponse = response.body()
            if (jokesResponse != null) {
                // Access data using movieResponse.results
                val list2 = jokesResponse.data.memes
                for(item in list2){
                    list.add(adapterModel(item.name,item.url))
                }
                Log.d("shivam" , "list : ${list.size}  list2 : ${list2.size} ")

                // Switch to the Main thread to update the UI
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                    Log.d("shivam", "list: ${list.size}  list2: ${list2.size}")
                }

            } else {
                Log.d("xx" , "Null")
            }
        }



    }
}