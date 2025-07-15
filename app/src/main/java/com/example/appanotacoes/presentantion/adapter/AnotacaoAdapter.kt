package com.example.appanotacoes.presentantion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appanotacoes.data.entity.Anotacao
import com.example.appanotacoes.databinding.ItemAnotacaoBinding
import com.example.appanotacoes.help.ConversorData

class AnotacaoAdapter (private val onClickDeletar:((Anotacao)->Unit)?=null,private val onclickEditar:((Anotacao)->Unit)?=null): RecyclerView.Adapter<AnotacaoAdapter.AnotacaoViewHolder>() {

    private var listaAnotacoes = listOf<Anotacao>()
    fun adicionarLista(list:List<Anotacao>){
        listaAnotacoes= list
        notifyDataSetChanged()
    }

    inner class AnotacaoViewHolder(private val binding: ItemAnotacaoBinding):ViewHolder(binding.root){
        fun bind(nota: Anotacao){
            with(binding){
                textTituloNota.text = nota.titulo
                textDataNota.text = ConversorData.formatarDataAbreviada(nota.data)
                cardAnotacao.setCardBackgroundColor(ContextCompat.getColor(binding.root.context,nota.cor))

                cardAnotacao.setOnLongClickListener {
                    onClickDeletar?.invoke(nota)
                    true
                }
                cardAnotacao.setOnClickListener {
                    onclickEditar?.invoke(nota)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacaoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAnotacaoBinding.inflate(layoutInflater,parent,false)
        return AnotacaoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val lista = listaAnotacoes[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int {
        return listaAnotacoes.size
    }

}