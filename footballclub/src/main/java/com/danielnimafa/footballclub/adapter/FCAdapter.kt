package com.danielnimafa.footballclub.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danielnimafa.footballclub.model.FootballClub
import org.jetbrains.anko.*
import java.util.*
import kotlin.collections.ArrayList

class FCAdapter(var list: ArrayList<FootballClub> = arrayListOf(),
                val itemTap: (FootballClub) -> Unit) : RecyclerView.Adapter<FCAdapter.ItemHolder>() {

    lateinit var activity: Activity

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ItemHolder {
        activity = parent.context as Activity
        return ItemHolder(FootballClubRowUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.let {
            with(list[pos]) {
                it.labelTx.text = label
                it.imgThumb.setImageDrawable(activity.getDrawable(img))
                it.itemView.setOnClickListener { itemTap(this) }
            }
        }
    }

    fun addCollectionData(fclubs: ArrayList<FootballClub>) {
        list.addAll(fclubs)
        notifyDataSetChanged()
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {

        var labelTx: TextView
        var imgThumb: ImageView

        init {
            labelTx = v.findViewById(FootballClubRowUI.labelTx)
            imgThumb = v.findViewById(FootballClubRowUI.imgClub)
        }

    }

}

class FootballClubRowUI() : AnkoComponent<ViewGroup> {

    companion object {
        val labelTx = 1
        val imgClub = 2
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPaddingRelative(dip(0), dip(8), dip(0), dip(8))

            imageView {
                id = imgClub
            }.lparams {
                width = dip(32)
                height = dip(32)
                marginStart = dip(16)
                marginEnd = dip(16)
            }

            textView {
                id = labelTx
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