package com.fpliu.newton.font.config.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fpliu.newton.util.startActivity
import com.fpliu.newton.font.config.ui.FontListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(FontListActivity::class)
        finish()
    }
}