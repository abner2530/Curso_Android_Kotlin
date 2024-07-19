package com.example.aula_19_recycler.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aula_19_recycler.adapter.CarroListAdapter
import com.example.aula_19_recycler.data.CarroMock
import com.example.aula_19_recycler.databinding.ActivityMainBinding
import com.example.aula_19_recycler.model.Carro

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CarroListAdapter
    private lateinit var mock: CarroMock
    private var pos = -1

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mock = CarroMock()

        adapter = CarroListAdapter(mock.listaCarros, CarroListAdapter.OnclickListener { carro ->
            //Toast.makeText(applicationContext, carro.modelo, Toast.LENGTH_SHORT).show()
            pos = pesquisarPosicao(carro.id)
            binding.editModelo.setText(mock.listaCarros[pos].modelo)
        })

        binding.recyclerView.adapter = adapter

        binding.buttonInserir.setOnClickListener {
            val modelo = binding.editModelo.text.toString().toInt()
            mock.listaCarros.add(Carro(modelo, modelo.toString()))
            adapter.notifyDataSetChanged()

        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val modelo = binding.editModelo.text.toString()
                mock.listaCarros[pos].modelo = modelo
                mock.listaCarros[pos].id = modelo.toInt()
                pos = -1
                adapter.notifyDataSetChanged()
            }
        }

        binding.buttonDeletar.setOnClickListener {
            if (pos >= 0) {
                val modelo = binding.editModelo.text.toString()
                mock.listaCarros.removeAt(pos)
                pos = -1
                adapter.notifyDataSetChanged()
            }

        }
    }

    private fun pesquisarPosicao(id: Int): Int {
        for (i in 0..mock.listaCarros.size) {
            if (mock.listaCarros[i].id == id) {
                return i
            }
        }
        return -1
    }
}