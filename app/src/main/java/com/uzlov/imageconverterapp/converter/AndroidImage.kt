package com.uzlov.imageconverterapp.converter

import com.uzlov.imageconverterapp.converter.interfaces.IImage
import java.io.File
import java.io.FileNotFoundException

class AndroidImage(private val p: File) : IImage {

    init {
        if (!p.exists()) throw FileNotFoundException("You pass invalid path file!")
    }
    override fun getPath(): String {
        return p.path
    }
}