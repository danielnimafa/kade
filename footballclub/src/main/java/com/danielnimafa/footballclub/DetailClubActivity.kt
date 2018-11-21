package com.danielnimafa.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class DetailClubActivity : AppCompatActivity() {

    lateinit var clubNameTx: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
    }

    private fun setupContentView() {
        verticalLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            gravity = LinearLayout.HORIZONTAL
            padding = dip(16)

            clubNameTx = textView {
                text = "-"
                textSize = 14f
            }.lparams {
                width = wrapContent
                height = wrapContent
                marginEnd = dip(16)
            }
        }
    }
}