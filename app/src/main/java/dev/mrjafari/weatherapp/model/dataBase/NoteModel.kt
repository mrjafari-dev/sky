package dev.mrjafari.weatherapp.model.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  NoteModel(
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "text")
    val text:String

)
