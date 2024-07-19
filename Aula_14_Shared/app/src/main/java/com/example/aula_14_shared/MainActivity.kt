package com.example.aula_14_shared

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aula_14_shared.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = this.getSharedPreferences("valores", Context.MODE_PRIVATE)
        val valor = sharedPreferences.getString("valor", "")
        binding.textValor.text = valor

        binding.buttonGravar.setOnClickListener {
            val novoValor = binding.editValor.text.toString()
            binding.textValor.text = novoValor

            val editor = sharedPreferences.edit()
            editor.putString("valor", novoValor)
            editor.apply()
        }

    }
}