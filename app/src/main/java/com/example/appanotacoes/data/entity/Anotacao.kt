package com.example.appanotacoes.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appanotacoes.costantes.Constantes
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constantes.TABLE_NAME)
@Parcelize
data class Anotacao(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_anotacao")
    val idAnotacao: Long=0L,
    val titulo: String,
    val anotacao: String,
    val data: Long = System.currentTimeMillis(),
    val cor: Int
): Parcelable
