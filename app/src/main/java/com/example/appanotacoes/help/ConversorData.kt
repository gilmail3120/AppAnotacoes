package com.example.appanotacoes.help

import java.text.SimpleDateFormat
import java.util.Locale

object ConversorData {
    fun formatarDataAbreviada(data:Long):String{
        val dataFormatada = SimpleDateFormat("dd  MMMM", Locale("pt","BR"))
        return dataFormatada.format(data)
    }
    fun formatarDataCompleta(data: Long):String{
        val formatador = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt","BR"))
        return formatador.format(data)
    }
}