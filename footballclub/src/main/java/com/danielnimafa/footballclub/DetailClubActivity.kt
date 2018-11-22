package com.danielnimafa.footballclub

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danielnimafa.footballclub.model.FootballClubParcel
import org.jetbrains.anko.*

class DetailClubActivity : AppCompatActivity() {

    lateinit var clubNameTx: TextView
    lateinit var clubDescTx: TextView
    lateinit var imgClub: ImageView
    var footballClub: FootballClubParcel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
        intent?.extras?.run {
            footballClub = getParcelable("fc")
        }

        attachData()
    }

    private fun attachData() {
        footballClub?.let {
            imgClub.setImageResource(it.img)
            clubNameTx.text = it.label
            clubDescTx.text = it.desc
        }
    }

    private fun setupContentView() {
        verticalLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            gravity = LinearLayout.HORIZONTAL
            padding = dip(16)

            imgClub = imageView {
                setImageResource(R.drawable.inter)
            }.lparams {
                width = dip(96)
                height = dip(96)
                topMargin = dip(16)
                bottomMargin = dip(16)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            clubNameTx = textView {
                text = "-"
                textSize = 18f
            }.lparams {
                width = wrapContent
                height = wrapContent
                marginEnd = dip(16)
                marginStart = dip(16)
                bottomMargin = dip(16)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            clubDescTx = textView {
                text = "-"
                textSize = 14f
            }.lparams {
                width = wrapContent
                height = wrapContent
                marginEnd = dip(16)
                marginStart = dip(16)
                gravity = Gravity.LEFT
            }
        }
    }
}