package com.karn.dictionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_word_definition.*

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)

        intent.getStringExtra(KEY)

        definition_text_view.text = intent.getStringExtra(KEY)

        back_image_view.setOnClickListener { finish() }
    }
}