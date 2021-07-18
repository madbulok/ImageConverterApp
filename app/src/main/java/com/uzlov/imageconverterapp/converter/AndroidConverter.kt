package com.uzlov.imageconverterapp.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.uzlov.imageconverterapp.converter.interfaces.IConverter
import com.uzlov.imageconverterapp.converter.interfaces.IImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class AndroidConverter(private val schedulers: Scheduler) : IConverter {

    override fun convert(image: IImage): Completable {
        return Completable
            .fromAction {
                val bitmap = BitmapFactory.decodeFile(image.getPath())
                val outputFile = File("/storage/emulated/0/12222.png")
                if (!outputFile.exists()) outputFile.createNewFile()
                val out = FileOutputStream(outputFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }
            .observeOn(schedulers)
            .subscribeOn(Schedulers.newThread())
            .doOnError {
                Log.e("TAG", "ERROR !!")
            }
    }
}