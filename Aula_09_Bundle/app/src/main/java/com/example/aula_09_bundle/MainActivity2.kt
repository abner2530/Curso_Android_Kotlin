package com.example.aula_09_bundle

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_09_bundle.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val i  = intent
        val bundle = i.extras

        binding.buttonOk.setOnClickListener {
            val numero = binding.editNumero.text.toString().trim()

            if(numero.isEmpty()){
                Toast.makeText(applicationContext, "Preencha o campo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val i = Intent(this, MainActivity3::class.java)
            if (bundle != null) {
                i.putExtras(bundle)
            }
            i.putExtra("numero2", numero.toInt())
            startActivity(i)
        }
    }
}