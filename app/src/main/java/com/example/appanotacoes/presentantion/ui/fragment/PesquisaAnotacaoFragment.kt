package com.example.appanotacoes.presentantion.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appanotacoes.R
import com.example.appanotacoes.costantes.Constantes
import com.example.appanotacoes.databinding.FragmentPesquisaAnotacaoBinding
import com.example.appanotacoes.help.Alerta
import com.example.appanotacoes.presentantion.adapter.AnotacaoAdapter
import com.example.appanotacoes.presentantion.ui.activity.CriacaoAnotacaoActivity
import com.example.appanotacoes.presentantion.viewmodel.AnotacaoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class PesquisaAnotacaoFragment : Fragment() {
    private lateinit var binding: FragmentPesquisaAnotacaoBinding
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()
    private lateinit var anotacaoAdapter: AnotacaoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPesquisaAnotacaoBinding.inflate(inflater, container, false)

        iniciarAdapter()
        searchView()
        observable()

        return binding.root
    }

    private fun observable() {
        anotacaoViewModel.listaAnotacao.observe(viewLifecycleOwner) { lista ->
            anotacaoAdapter.adicionarLista(lista)
        }
    }

    private fun iniciarAdapter() {
        anotacaoAdapter = AnotacaoAdapter(
            onClickDeletar = {anotacao-> Alerta.mostrarConfirmacao(requireContext(),{anotacaoViewModel.deletar(anotacao)})},
            onclickEditar = { anotacao ->
                val intent = Intent(requireContext(), CriacaoAnotacaoActivity::class.java)
                intent.putExtra(Constantes.TABLE_NAME, anotacao)
                startActivity(intent)
            }
        )
        binding.rvAnotacoesPesquisa.adapter = anotacaoAdapter
        binding.rvAnotacoesPesquisa.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL, false
        )
    }


    private fun searchView() {
        binding.searchAnotacao.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    anotacaoViewModel.pesquisarAnotacao(newText)
                }
                return true
            }
        })
    }

}