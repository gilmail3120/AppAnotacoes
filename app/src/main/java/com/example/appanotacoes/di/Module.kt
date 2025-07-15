package com.example.appanotacoes.di

import android.content.Context
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

object Module {
    @Provides
    fun provideBancadoDados(@ApplicationContext context: Context): DataBase {
        return DataBase.getInstance(context)
    }

    @Provides
    fun provideAnotacaoDAO(dataBase: DataBase): AnotacaoDAO {
        return dataBase.anotacaoDAO
    }

    @Provides
    fun provideAnotacaoRepository(anotacaoDAO: AnotacaoDAO): AnotacaoRepository {
        return AnotacaoRepositoryImpl(anotacaoDAO)
    }
}