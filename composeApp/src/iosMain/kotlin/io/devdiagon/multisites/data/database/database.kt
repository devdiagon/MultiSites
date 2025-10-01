package io.devdiagon.multisites.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<SitesDB> {
    val dbFilePath = NSHomeDirectory() + "/$DB_NAME"
    return Room.databaseBuilder<SitesDB>(
        name = dbFilePath,
        factory = { SitesDB::class.instantiateImp() }
    ).setDriver(BundledSQLiteDriver())
}