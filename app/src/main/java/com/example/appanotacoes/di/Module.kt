package com.example.appanotacoes.di

import android.content.Context
import com.example.appanotacoes.data.dao.AnotacaoDAO
import com.example.appanotacoes.data.database.DataBase
import com.example.appanotacoes.data.repository.AnotacaoRepository
import com.example.appanotacoes.data.repository.AnotacaoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
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