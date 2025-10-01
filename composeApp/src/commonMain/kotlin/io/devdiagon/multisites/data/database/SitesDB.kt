package io.devdiagon.multisites.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.devdiagon.multisites.data.models.Site

const val DB_NAME = "sites.db"

@Database(entities = [Site::class], version = 1)
abstract class SitesDB: RoomDatabase() {
    abstract fun sitesDao(): SitesDao
}