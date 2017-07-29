package com.example.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by JJBOOM on 2017/7/29.
 */
class SettingsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}