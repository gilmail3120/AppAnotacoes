package com.example.appanotacoes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appanotacoes.costantes.Constantes
import com.example.appanotacoes.data.dao.AnotacaoDAO
import com.example.appanotacoes.data.entity.Anotacao

@Database(
    entities = [Anotacao::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {
    //daos
    abstract val anotacaoDAO: AnotacaoDAO


    companion object {
        fun getInstance(context: Context): DataBase {
            return Room.databaseBuilder(
                context,DataBase::class.java,
                Constantes.NOME_DB
            ).build()
        }
    }
}