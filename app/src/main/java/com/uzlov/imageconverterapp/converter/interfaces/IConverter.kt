package com.uzlov.imageconverterapp.converter.interfaces

import io.reactivex.rxjava3.core.Completable

interface IConverter {
    fun convert(image: IImage) : Completable
}