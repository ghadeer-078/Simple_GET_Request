package com.example.simple_get_request

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_get_request.API.APIClient
import com.example.simple_get_request.API.APIInterface
import com.example.simple_get_request.Model.PeopleDetailed
import com.example.simple_get_request.Resource.RVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var info: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        info = arrayListOf()
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(info)


        //an object from APIInterface to call the method
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        //progress dialog to show that something happened
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.getUser()?.enqueue(object : Callback<List<PeopleDetailed>> {
                override fun onResponse(
                    call: Call<List<PeopleDetailed>>,
                    response: Response<List<PeopleDetailed>>
                ) {
                    progressDialog.dismiss()
                    for (User in response.body()!!) {
                        info.add(User.name.toString())
                    }
                    rvMain.adapter = rvAdapter
                    rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvMain.adapter?.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<PeopleDetailed>>, t: Throwable) {
                    //  onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

}