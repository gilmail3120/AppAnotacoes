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
import com.example.appanotacoes.costantes.Constantes
import com.example.appanotacoes.databinding.FragmentPrincipalBinding
import com.example.appanotacoes.help.Alerta
import com.example.appanotacoes.presentantion.adapter.AnotacaoAdapter
import com.example.appanotacoes.presentantion.ui.activity.CriacaoAnotacaoActivity
import com.example.appanotacoes.presentantion.viewmodel.AnotacaoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PrincipalFragment : Fragment() {

    private lateinit var binding: FragmentPrincipalBinding
    private lateinit var anotacaoAdapter: AnotacaoAdapter
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(inflater, container, false)

        iniciarAdapter()
        observable()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        anotacaoViewModel.listar()
    }

    private fun observable() {
        anotacaoViewModel.listaAnotacao.observe(viewLifecycleOwner) { listaAnotacoes ->
            anotacaoAdapter.adicionarLista(listaAnotacoes)
        }
    }

    private fun iniciarAdapter() {
        anotacaoAdapter = AnotacaoAdapter(
            onClickDeletar = { anotacao ->
                Alerta.mostrarConfirmacao(requireContext(),
                    { anotacaoViewModel.deletar(anotacao) })
            },
            onclickEditar = { anotacao ->
                val intent = Intent(requireContext(), CriacaoAnotacaoActivity::class.java)
                intent.putExtra(Constantes.TABLE_NAME, anotacao)
                startActivity(intent)
            }
        )
        binding.rvAnotacoes.adapter = anotacaoAdapter
        binding.rvAnotacoes.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

}