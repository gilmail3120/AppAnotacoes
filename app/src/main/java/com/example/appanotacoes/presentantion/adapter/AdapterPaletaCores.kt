package com.example.appanotacoes.presentantion.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appanotacoes.R
import com.example.appanotacoes.databinding.ItemPaletaCoresBinding
import com.example.appanotacoes.presentantion.adapter.AdapterPaletaCores.ViewHolderPaletadeCores

class AdapterPaletaCores (private val onCorSelecionada:(Int)->Unit) : RecyclerView.Adapter<ViewHolderPaletadeCores>(){
    private var paletaCores = listOf(
        R.color.vermelho_claro,
        R.color.rosa_claro,
        R.color.roxo_claro,
        R.color.verde_claro,
        R.color.amarelo_claro,
        R.color.cinza_claro)


    fun adicionar(lista: List<Int>) {
        paletaCores = lista
        notifyDataSetChanged()
    }

    inner class ViewHolderPaletadeCores(private val binding: ItemPaletaCoresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(corId:Int){
            val context = binding.root.context
            val cor = androidx.core.content.ContextCompat.getColor(context,corId)

            binding.viewCor.setBackgroundColor(cor)

            binding.viewCor.setOnClickListener {
                onCorSelecionada(corId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPaletadeCores {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPaletaCoresBinding.inflate(layoutInflater,parent,false)
        return ViewHolderPaletadeCores(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPaletadeCores, position: Int) {
        val lista = paletaCores[position]
        holder.bind(lista)
    }
    override fun getItemCount(): Int {
        return paletaCores.size
    }


}