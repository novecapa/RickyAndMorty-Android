package com.example.rickandmorty.manager.files

import com.example.rickandmorty.di.AppContainer
import java.io.File

object FileHelper {

    val getDocumentsDirectory: String
        get() {
            return AppContainer.context?.let { context ->
                context.filesDir.toString() + File.separator
            } ?: ""
        }
}