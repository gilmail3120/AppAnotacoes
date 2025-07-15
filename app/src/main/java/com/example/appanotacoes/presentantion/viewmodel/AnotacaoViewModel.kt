package com.example.appanotacoes.presentantion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appanotacoes.data.entity.Anotacao
import com.example.appanotacoes.data.repository.AnotacaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AnotacaoViewModel @Inject constructor(private val anotacaoRepository: AnotacaoRepository) :
    ViewModel() {

    private val _listaAnotacao = MutableLiveData<List<Anotacao>>()
    val listaAnotacao: LiveData<List<Anotacao>>
        get() = _listaAnotacao

    fun salvar(anotacao: Anotacao) {
        viewModelScope.launch(Dispatchers.IO) {
            //val anotacao = Anotacao(titulo = titulo, anotacao = anotacao, cor = cor)
            anotacaoRepository.salvar(anotacao)

        }
    }

    fun editar(anotacao: Anotacao) {
        viewModelScope.launch(Dispatchers.IO) {
            anotacaoRepository.editar(anotacao)
        }
    }

    fun listar() {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = anotacaoRepository.listar()
            _listaAnotacao.postValue(lista)
        }
    }

    fun pesquisarAnotacao(pesquisa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = anotacaoRepository.pesquisaAnotacao(pesquisa)
            _listaAnotacao.postValue(lista)
        }
    }

    fun deletar(anotacao: Anotacao) {
        viewModelScope.launch(Dispatchers.IO) {
            anotacaoRepository.deletar(anotacao)
            listar()
        }
    }

}

