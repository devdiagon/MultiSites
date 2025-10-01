package io.devdiagon.multisites.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SitesDB> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DB_NAME)
    return Room.databaseBuilder(
        context = appContext,
        name = dbFile.absolutePath,
    )
}