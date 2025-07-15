package com.example.appanotacoes.presentantion.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appanotacoes.R
import com.example.appanotacoes.costantes.Constantes
import com.example.appanotacoes.data.entity.Anotacao
import com.example.appanotacoes.databinding.ActivityCriacaoAnotacaoBinding
import com.example.appanotacoes.presentantion.adapter.AdapterPaletaCores
import com.example.appanotacoes.presentantion.viewmodel.AnotacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CriacaoAnotacaoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCriacaoAnotacaoBinding.inflate(layoutInflater) }
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()
    private var anotacaoEditar: Anotacao? = null
    private lateinit var adapterPaletaCores: AdapterPaletaCores
    private var selecaoDeCor = R.color.amarelo_claro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bundleAnotacao()
        eventoCLique()
        paletaCores()
    }

    private fun paletaCores() {
        adapterPaletaCores = AdapterPaletaCores() { corSelecionada ->
            selecaoDeCor = corSelecionada
        }
        binding.rvPaletaCores.adapter = adapterPaletaCores
        binding.rvPaletaCores.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }


    private fun bundleAnotacao() {
        val bundle = intent.extras
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                anotacaoEditar = bundle.getParcelable(Constantes.TABLE_NAME, Anotacao::class.java)
            } else {
                anotacaoEditar = bundle.getParcelable(Constantes.TABLE_NAME)
            }
            if (anotacaoEditar != null) {
                binding.editInputTitulo.setText(anotacaoEditar!!.titulo)
                binding.editInputAnotacao.setText(anotacaoEditar!!.anotacao)
                selecaoDeCor =anotacaoEditar!!.cor

            }
        }
    }

    private fun eventoCLique() {
        with(binding) {
            binding.btnSalvar.setOnClickListener {
                val titulo = editInputTitulo.text.toString()
                val anotacao = editInputAnotacao.text.toString()

                if (anotacaoEditar != null) {
                    val anotacaoEditar = Anotacao(
                        idAnotacao = anotacaoEditar!!.idAnotacao,
                        titulo = titulo,
                        anotacao = anotacao,
                        cor = selecaoDeCor
                    )
                    anotacaoViewModel.editar(anotacaoEditar)
                } else {
                    anotacaoViewModel.salvar(
                        Anotacao(
                            titulo = titulo,
                            anotacao = anotacao,
                            cor = selecaoDeCor
                        )
                    )
                }
                finish()
            }
        }
    }
}