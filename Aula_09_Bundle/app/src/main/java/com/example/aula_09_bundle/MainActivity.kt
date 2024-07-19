package com.example.aula_09_bundle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_09_bundle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOk.setOnClickListener {
            val numero = binding.editNumero.text.toString().trim()

            if(numero.isEmpty()){
                Toast.makeText(applicationContext, "Preencha o campo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("numero1", numero.toInt())
            startActivity(i)
        }
    }
}