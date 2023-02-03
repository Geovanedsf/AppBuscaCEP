package com.geovane.appapigit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.geovane.appapigit.model.Address
import com.geovane.appapigit.network.EndPointPath
import com.geovane.appapigit.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var address: EditText
    private lateinit var bairro: EditText
    private lateinit var city: EditText
    private lateinit var state: EditText
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        address = findViewById(R.id.editTextLogradouro)
        address.isEnabled = false
        bairro = findViewById(R.id.editTextBairro)
        bairro.isEnabled = false
        city = findViewById(R.id.editTextCidade)
        city.isEnabled = false
        state = findViewById(R.id.editTextEstado)
        state.isEnabled = false
        button = findViewById(R.id.buttonOne)

        button.setOnClickListener {
            getData(address, bairro, city, state)
        }

    }

    private fun getData(address: EditText, bairro: EditText, city: EditText, state: EditText) {

        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPath = retrofitBase.create(EndPointPath::class.java)
        val cep = findViewById<EditText>(R.id.editTextCep).text
        val callback = endPointPath.getAddress(cep.toString())

        callback.enqueue(object: Callback<Address> {
            override fun onResponse(call: Call<Address>, response: Response<Address>) {

                address.setText(response.body()?.address).toString()
                bairro.setText(response.body()?.bairro).toString()
                city.setText(response.body()?.city).toString()
                state.setText(response.body()?.state).toString()


            }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Não funcionou", Toast.LENGTH_LONG).show()
            }
        })
    }
}
