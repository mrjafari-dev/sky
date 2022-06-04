package dev.mrjafari.weatherapp.model.dataBase

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NoteDao {
    @Insert
    fun insertNote (noteModel: NoteModel)
}