package com.uzlov.imageconverterapp.presenters

import com.uzlov.imageconverterapp.converter.interfaces.IConverter
import com.uzlov.imageconverterapp.converter.interfaces.IImage
import com.uzlov.imageconverterapp.ui.ConvertFragmentView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class ConvertPresenter(private val converter: IConverter) :
    MvpPresenter<ConvertFragmentView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun convertJpegFile(jpegFilePath: IImage) {
        viewState.showProcessConverting()
        val disposable = converter.convert(jpegFilePath)
            .subscribe({
                viewState.hideProcessConverting()
            }, {
                viewState.errorCancelProcessConverting(it.message!!)
            })
        compositeDisposable.add(disposable)
    }

    fun stopConvert() {
        compositeDisposable.dispose()
        viewState.hideProcessConverting()
    }
}