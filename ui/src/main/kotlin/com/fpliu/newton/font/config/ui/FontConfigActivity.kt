package com.fpliu.newton.font.config.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.fpliu.newton.font.config.api.FontConfigFactory
import com.fpliu.newton.font.config.api.createTypeface
import com.fpliu.newton.font.config.ui.entity.Font
import com.fpliu.newton.ui.base.BaseActivity
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.autoDisposable
import kotlinx.android.synthetic.main.font_config_activity.*
import kotlin.system.exitProcess

class FontConfigActivity : BaseActivity() {

    companion object {

        private const val KEY_FONT = "font"

        fun start(activity: BaseActivity, font: Font) {
            Intent(activity, FontConfigActivity::class.java).apply {
                putExtra(KEY_FONT, font)
            }.let { activity.startActivity(it) }
        }
    }

    private lateinit var font: Font

    override fun onCreate(savedInstanceState: Bundle?) {
        font = if (savedInstanceState == null) {
            intent?.getParcelableExtra(KEY_FONT) ?: Font()
        } else {
            savedInstanceState.getParcelable(KEY_FONT) ?: Font()
        }

        super.onCreate(savedInstanceState)

        title = font.name

        addContentView(R.layout.font_config_activity)

        listOf(findViewById(R.id.base_view_head_title), applyBtn, tv1, tv2, tv3).forEach {
            it.typeface = createTypeface(font.type, font.path)
        }
        val uri = "${font.type}:${font.path}"
        val fontSetting = FontConfigFactory.getInstance(applicationContext)
        if (fontSetting.getCurrentFontUri() == uri) {
            applyBtn.visibility = View.INVISIBLE
        } else {
            applyBtn.clicks().autoDisposable(disposeOnDestroy()).subscribe {
                fontSetting.changeFont(uri)
                exitProcess(0)
            }
        }
    }
}