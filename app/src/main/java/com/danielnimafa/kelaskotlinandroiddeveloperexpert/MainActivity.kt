package com.danielnimafa.kelaskotlinandroiddeveloperexpert

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainUI().setContentView(this)
    }

    class MainUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                padding = dip(16)

                // edittext
                val name = editText {
                    hint = "What's your name"
                }

                // button show toast
                button("Halo") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener { toast("Halo, ${name.text}!") }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                // button show alert
                button("Show Alert") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener {
                        alert("Happy Coding", "Hello ${name.text}!") {
                            yesButton { toast("Wow.."); it.dismiss() }
                            noButton { it.dismiss() }
                        }.show()
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                // button show Selector
                button("Show Selector") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener {
                        val club = listOf("Barcelona", "Real Madrid", "Bayern Munchen", "Liverpool")
                        selector("Halo, ${name.text}! Apa club soccer favoritmu?", club) { _, i ->
                            toast("Pasti ${club[i]} kan?")
                        }
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                // button show snackbar
                button("Show Snackbar") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener { snackbar("Halo, ${name.text}!") }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

                // button goto SecondActivity
                button("Go To Second Activity!") {
                    backgroundColor = ContextCompat.getColor(context, R.color.colorAccent)
                    textColor = Color.WHITE
                    setOnClickListener { startActivity<SecondActivity>("name" to "${name.text}") }
                }.lparams(width = matchParent) {
                    topMargin = dip(5)
                }

            }
        }

    }
}
