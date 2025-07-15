package com.example.appanotacoes.help

import android.app.AlertDialog
import android.content.Context

object Alerta {
    fun mostrarConfirmacao(context: Context, onConfirmacao: () -> Unit) {
        AlertDialog.Builder(context)
            .setTitle("Confirmar exclusão")
            .setMessage("Gostaria de excluir este item?")
            .setPositiveButton("Sim") { dialog, _ ->
                onConfirmacao()
                dialog.dismiss()
            }
            .setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}