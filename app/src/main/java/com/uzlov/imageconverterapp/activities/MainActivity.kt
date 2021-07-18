package com.uzlov.imageconverterapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uzlov.imageconverterapp.R
import com.uzlov.imageconverterapp.fragments.ConvertFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, ConvertFragment.newInstance())
            .commit()
    }
}