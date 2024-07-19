package com.example.aula_20_banco

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aula_20_banco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        val listaUtilizadores = db.utilizadorListSelectAll()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            binding.textId.text = "ID: ${listaUtilizadores[position].id}"
            binding.editUsername.setText(listaUtilizadores[position].username)
            binding.editPassword.setText(listaUtilizadores[position].password)
            pos = position
        }

        binding.buttonInserir.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            val res = db.utilizadorInsert(username, password)

            if (res > 0) {
                Toast.makeText(applicationContext, "Insert OK: $res", Toast.LENGTH_SHORT).show()
                listaUtilizadores.add(Utilizador(res.toInt(), username, password))
                binding.editUsername.setText("")
                binding.editPassword.setText("")
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "Erro when inserting! ", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.buttonEditar.setOnClickListener {
            if (pos >= 0) {
                val id = listaUtilizadores[pos].id
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()

                val res = db.utilizadorUpdate(id, username, password)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()
                    listaUtilizadores[pos].username = username
                    listaUtilizadores[pos].password = password
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "Erro when Updating! ", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

        binding.buttonDeletar.setOnClickListener {
            if (pos >= 0) {
                val id = listaUtilizadores[pos].id
                val res = db.utilizadorDelete(id)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Delete OK: $res", Toast.LENGTH_SHORT).show()
                    listaUtilizadores.removeAt(pos)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "Erro when deleting! ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}