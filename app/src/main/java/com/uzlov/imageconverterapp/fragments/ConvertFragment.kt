package com.uzlov.imageconverterapp.fragments

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.uzlov.imageconverterapp.R
import com.uzlov.imageconverterapp.converter.AndroidConverter
import com.uzlov.imageconverterapp.converter.AndroidImage
import com.uzlov.imageconverterapp.databinding.ConvertFragmentLayoutBinding
import com.uzlov.imageconverterapp.presenters.ConvertPresenter
import com.uzlov.imageconverterapp.ui.ConvertFragmentView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.File

class ConvertFragment : MvpAppCompatFragment(), ConvertFragmentView {

    private var processDialog: AlertDialog? = null
    private val presenter by moxyPresenter {
        ConvertPresenter(AndroidConverter(AndroidSchedulers.mainThread()))
    }
    private var vb: ConvertFragmentLayoutBinding? = null

    companion object {
        fun newInstance(): ConvertFragment {
            return ConvertFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ConvertFragmentLayoutBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.btnStartConvertFile?.setOnClickListener {
            val file = File(Environment.getExternalStorageDirectory().path + "/12.jpg")
            val image = AndroidImage(file)
            presenter.convertJpegFile(image)
        }

        processDialog = AlertDialog.Builder(requireContext()).apply {
            setTitle("JPG -> PNG ...")
            setView(R.layout.dialog_content_layout)
            setPositiveButton(R.string.cancel_string) { _, _ ->
                presenter.stopConvert()
            }
        }.create()
    }

    override fun showProcessConverting() {
        vb?.btnStartConvertFile?.isEnabled = false
        processDialog?.show()
    }

    override fun hideProcessConverting() {
        vb?.btnStartConvertFile?.isEnabled = true
        processDialog?.cancel()
    }

    override fun cancelProcessConverting() {
        vb?.btnStartConvertFile?.isEnabled = false
        processDialog?.cancel()
    }

    override fun errorCancelProcessConverting(message: String) {
        processDialog?.cancel()
        showMessage(message)
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}