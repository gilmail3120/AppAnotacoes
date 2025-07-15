package com.example.appanotacoes.data.repository

import com.example.appanotacoes.data.dao.AnotacaoDAO
import com.example.appanotacoes.data.entity.Anotacao
import javax.inject.Inject

class AnotacaoRepositoryImpl @Inject constructor(private val anotacaoDAO: AnotacaoDAO): AnotacaoRepository {
    override suspend fun salvar(anotacao: Anotacao) {
        anotacaoDAO.salvar(anotacao)
    }

    override suspend fun editar(anotacao: Anotacao) {
        anotacaoDAO.editar(anotacao)
    }

    override suspend fun listar(): List<Anotacao> {
        return anotacaoDAO.listar()
    }

    override suspend fun deletar(anotacao: Anotacao) {
        anotacaoDAO.deletar(anotacao)
    }

    override suspend fun pesquisaAnotacao(pesquisa: String): List<Anotacao> {
        return anotacaoDAO.pesquisa(pesquisa)
    }
}