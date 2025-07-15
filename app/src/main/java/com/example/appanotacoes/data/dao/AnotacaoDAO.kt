package com.example.appanotacoes.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appanotacoes.data.entity.Anotacao

@Dao
interface AnotacaoDAO {
    @Insert
    fun salvar(anotacao: Anotacao):Long

    @Update
    fun editar(anotacao: Anotacao):Int

    @Query("select * from anotacoes")
    fun listar(): List<Anotacao>

    @Delete
    fun deletar(anotacao: Anotacao):Int

    @Query("select * from anotacoes where titulo like'%'||:pesquisa||'%' or anotacao like '%'||:pesquisa||'%'")
    fun pesquisa(pesquisa:String):List<Anotacao>
}