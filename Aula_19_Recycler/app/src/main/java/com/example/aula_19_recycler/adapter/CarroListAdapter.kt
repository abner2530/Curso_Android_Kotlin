package com.example.aula_19_recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aula_19_recycler.R
import com.example.aula_19_recycler.model.Carro

class CarroListAdapter(val listaCarros: ArrayList<Carro>, val onclickListener: OnclickListener) :
    RecyclerView.Adapter<CarroListAdapter.CarroViewHolder>() {

    var contadorOnCreate = 0
    var contadorOnBind = 0

    class CarroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_modelo)
    }

    class OnclickListener(val clickListener: (carro: Carro) -> Unit) {
        fun onClick(carro: Carro) = clickListener(carro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        contadorOnCreate++
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_caro_list, parent, false)

        return CarroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        contadorOnBind++
        val carro = listaCarros[position]
        holder.textView.text = carro.modelo

        holder.itemView.setOnClickListener {
            onclickListener.onClick(carro)
        }
    }
}