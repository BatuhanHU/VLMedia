package com.batuhan.vlmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.batuhan.vlmedia.adapter.RecyclerListViewAdapter
import com.batuhan.vlmedia.model.RetroCharacterListResponse
import com.batuhan.vlmedia.model.RetroResult
import com.pallstock.pallshipping.network.RequestService
import com.pallstock.pallshipping.network.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var pageCount : Int = 1
    private var characterList : ArrayList<RetroResult> = arrayListOf()

    private lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCharacters(1)


        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerListViewAdapter(characterList, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerListView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter


        }
    }

    private fun getCharacters(page : Int){
        val service =
            RetrofitClientInstance.getRetrofitInstance().create(RequestService::class.java)
        val call = service.characters(page)
        call.enqueue(object : retrofit2.Callback<RetroCharacterListResponse>{
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(
                call: Call<RetroCharacterListResponse>?,
                response: Response<RetroCharacterListResponse>?
            ) {
                if(response!!.isSuccessful){
                    pageCount = response.body().info.pages
                    response.body().results
                    characterList.addAll(response.body().results)
                }
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<RetroCharacterListResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })

    }
}