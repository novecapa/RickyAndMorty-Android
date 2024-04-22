package com.example.rickandmorty.manager.database

import com.example.rickandmorty.data.datasource.characters.database.models.RCharacter
import com.example.rickandmorty.data.datasource.characters.database.models.RLocation
import com.example.rickandmorty.manager.files.FileHelper
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import java.io.File

object RealmHelper {

    private val configuration = RealmConfiguration.Builder(
        setOf(
            RCharacter::class,
            RLocation::class
        )
    ).deleteRealmIfMigrationNeeded()
        .schemaVersion(0)
        // TODO: .migration(RMigrations())
        .compactOnLaunch { totalBytes, usedBytes ->
            // totalBytes refers to the size of the file on disk in bytes (data + free space)
            // usedBytes refers to the number of bytes used by data in the file
            // Compact if the file is over 30MB in size and less than 50% 'used'
            val thirtyMB = (30 * 1024 * 1024).toLong()
            totalBytes > thirtyMB && usedBytes / totalBytes < 0.5
            true
        }
        .directory(getPathFolder())
        .build()

    private val realmData = Realm.open(configuration)

    val realm: Realm
        get() = realmData

    private fun getPathFolder(): String {
        val folderPath = FileHelper.getDocumentsDirectory
        val dir = File(folderPath)
        if (!dir.exists())  {
            dir.mkdirs()
        }
        return folderPath
    }
}