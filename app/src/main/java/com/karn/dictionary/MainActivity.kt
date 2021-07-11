package com.karn.dictionary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)

        find_button.setOnClickListener {

            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
                        extractDefinitionFromJson(response)
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringRequest)
        }

    }

    private fun getUrl(): String {
        val word = word_edit_text.text
        val apiKey = "31c21e69-05d3-4338-b028-69608d1a33ec"
        return "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
    }

    private fun extractDefinitionFromJson(response: String) {

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)
    }
}