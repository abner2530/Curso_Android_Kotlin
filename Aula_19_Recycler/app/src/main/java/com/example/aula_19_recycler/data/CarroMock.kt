package com.example.aula_19_recycler.data

import com.example.aula_19_recycler.model.Carro

class CarroMock {
    var listaCarros = ArrayList<Carro>()

    init {
        for (i in 0  ..  5) {
            listaCarros.add(Carro(i, i.toString()))
        }
    }
}