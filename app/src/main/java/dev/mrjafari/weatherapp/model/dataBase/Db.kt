package dev.mrjafari.weatherapp.model.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class], version = 1)
abstract  class Db :RoomDatabase() {
    abstract fun noteDao() :NoteDao
}