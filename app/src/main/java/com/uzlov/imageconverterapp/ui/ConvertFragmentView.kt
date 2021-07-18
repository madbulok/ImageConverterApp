package com.uzlov.imageconverterapp.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ConvertFragmentView : MvpView {

    fun init()
    fun showProcessConverting()
    fun hideProcessConverting()
    fun cancelProcessConverting()
    fun errorCancelProcessConverting(message: String)
}
