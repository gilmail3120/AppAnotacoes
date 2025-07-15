package com.example.appanotacoes.data.repository

import com.example.appanotacoes.data.entity.Anotacao

interface AnotacaoRepository {
    suspend fun salvar(anotacao: Anotacao)
    suspend fun editar(anotacao: Anotacao)
    suspend fun listar(): List<Anotacao>
    suspend fun deletar(anotacao: Anotacao)
    suspend fun pesquisaAnotacao(pesquisa: String):List<Anotacao>

}