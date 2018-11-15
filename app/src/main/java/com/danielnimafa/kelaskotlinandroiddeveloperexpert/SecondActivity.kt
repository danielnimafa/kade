package com.danielnimafa.kelaskotlinandroiddeveloperexpert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

class SecondActivity : AppCompatActivity() {

    var nameValue: String = ""
    lateinit var nameTx: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            padding = dip(16)
            nameTx = textView()
        }

        intent?.extras?.let {
            nameTx.text = it.getString("name")
        }
    }

    companion object {
        operator fun get(context: Context) = Intent(context, SecondActivity::class.java)
    }
}
