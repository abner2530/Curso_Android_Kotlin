package com.example.aula_10_restaurante

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_10_restaurante.databinding.ActivityDadosPedidosBinding

class DadosPedidosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDadosPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val quant_cafe = i.getStringExtra("quant_cafe").toString().toInt()
        val quant_pao = i.getStringExtra("quant_pao").toString().toInt()
        val quant_chocolate = i.getStringExtra("quant_chocolate").toString().toInt()
        val preco_cafe = i.getDoubleExtra("preco_cafe", 0.0)
        val preco_pao = i.getDoubleExtra("preco_pao", 0.0)
        val preco_chocolate = i.getDoubleExtra("preco_chocolate", 0.0)


        val texto = "Resumo do Pedido:\n" +
                "| Item | Preço |\n" +
                "|-------|-------|\n" +
                "| Café | R$ ${quant_cafe * preco_cafe} |\n" +
                "| Pão | R$ ${quant_pao * preco_pao} |\n" +
                "| Chocolate | R$ ${quant_chocolate * preco_chocolate} |\n"

        binding.textResumo.setText(texto)
    }
}