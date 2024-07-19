package com.example.curso_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.curso_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if(!binding.editText.text.toString().isEmpty()) {
                val celsius: Double = binding.editText.text.toString().toDouble()
                val fahrenheit = celsius * 1.8 + 32

                binding.texto.text = "$fahrenheit °F"
            } else {
                binding.texto.text = "Temperatura Inválida"
            }
        }

        // Exemplo 2
        /* binding.button.setOnClickListener {
            val value : Double = binding.editText.text.toString().toDouble()
            val dolares = String.format("%.2f", value * 5.2)

            binding.texto.text = dolares.toString()
        }*/

        // Exemplo 1
        /* binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonOla.setOnClickListener {
            val nome : String = binding.editNome.text.toString()
            binding.dizerOla.text = "Olá " + nome
        }*/
    }
}