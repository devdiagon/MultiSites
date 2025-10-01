package io.devdiagon.multisites.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.devdiagon.multisites.data.models.Site
import kotlinx.coroutines.flow.Flow


// Methods to interact with the database
@Dao
interface SitesDao {
    @Query("SELECT * FROM Site")
    fun fetchRelevantSites(): Flow<List<Site>>

    @Query("SELECT * FROM Site WHERE id = :id")
    fun fetchSiteById(id: String): Flow<Site?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(sites: List<Site>)
}