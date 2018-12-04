package com.danielnimafa.footballclub.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danielnimafa.footballclub.model.FootballClubParcel
import org.jetbrains.anko.*

class FCAdapter(var list: ArrayList<FootballClubParcel> = arrayListOf(),
                val itemTap: (FootballClubParcel) -> Unit) : RecyclerView.Adapter<FCAdapter.ItemHolder>() {

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

    fun addCollectionData(fclubs: ArrayList<FootballClubParcel>) {
        list.addAll(fclubs)
        notifyDataSetChanged()
    }

    inner class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {

        var labelTx: TextView = v.findViewById(FootballClubRowUI.labelTx)
        var imgThumb: ImageView = v.findViewById(FootballClubRowUI.imgClub)

    }

}

class FootballClubRowUI() : AnkoComponent<ViewGroup> {

    companion object {
        const val labelTx = 1
        const val imgClub = 2
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