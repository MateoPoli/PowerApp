package com.google.mediapipe.examples.poselandmarker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext

class SquatForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squat_form)
        val textHeightField = findViewById<EditText>(R.id.textHeightField)
        val textWeightField = findViewById<EditText>(R.id.textWeightField)
        val nextBtn = findViewById<Button>(R.id.nextBtn)
        nextBtn.isEnabled = false

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                // Verificar si ambos campos de entrada tienen valores válidos
                val height = textHeightField.text.toString()
                val weight = textWeightField.text.toString()
                // Habilitar o deshabilitar el botón según las condiciones
                nextBtn.isEnabled = height.isNotEmpty() && weight.isNotEmpty()
            }
        }

        textHeightField.addTextChangedListener(watcher)
        textWeightField.addTextChangedListener(watcher)

        nextBtn.setOnClickListener {
            DataHelper.personHeight = textHeightField.text.toString().toDoubleOrNull() ?: 0.0
            DataHelper.barbellWeight = textWeightField.text.toString().toDoubleOrNull() ?: 0.0
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
