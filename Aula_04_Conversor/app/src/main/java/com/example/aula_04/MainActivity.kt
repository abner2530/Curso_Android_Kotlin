package com.example.aula_04

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aula_04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDolar.setOnClickListener {
            calcular_Taxa(5.25)
        }
        binding.buttonEuro.setOnClickListener {
            calcular_Taxa(5.65)
        }
    }

    private fun calcular_Taxa(taxa: Double) {
        val resultado = binding.editReais.text.toString().trim()

        if (resultado.isNotEmpty()) {
            val dolar = String.format("%.2f", resultado.toDouble() * taxa)
            binding.textDolares.text = "$dolar $"
        } else {
            Toast.makeText(applicationContext, "Valor inválido", Toast.LENGTH_SHORT).show()
        }
    }
}