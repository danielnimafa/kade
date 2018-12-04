package com.danielnimafa.klasemenliga.views.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.danielnimafa.klasemenliga.R
import com.danielnimafa.klasemenliga.model.MatchData

class ListMatchAdapter(val datasource: ArrayList<MatchData>,
                       private val itemTap: (MatchData) -> Unit) : RecyclerView.Adapter<ListMatchAdapter.ItemHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ItemHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.row_match_layout, container, false)
        return ItemHolder(view, itemTap)
    }

    override fun getItemCount(): Int = datasource.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindData(datasource[position])
    }

    fun addCollectionData(list: ArrayList<MatchData>) {
        datasource.addAll(list)
        notifyDataSetChanged()
    }

    inner class ItemHolder(view: View, itemTap: (MatchData) -> Unit) : RecyclerView.ViewHolder(view) {

        val dateTx: TextView = view.findViewById(R.id.dateTx)
        val homeTeamTx: TextView = view.findViewById(R.id.homeTeamTx)
        val homeScoreTx: TextView = view.findViewById(R.id.homeScoreTx)
        val awayScoreTx: TextView = view.findViewById(R.id.awayScoreTx)
        val awayTeamTx: TextView = view.findViewById(R.id.awayTeamTx)

        fun bindData(matchData: MatchData) {
            with(matchData) {
                dateTx.text = strDate
                homeTeamTx.text = strHomeTeam
                homeScoreTx.text = intHomeScore ?: "-"
                awayTeamTx.text = strAwayTeam
                awayScoreTx.text = intAwayScore ?: "-"
                itemView.setOnClickListener { itemTap(this) }
            }
        }

    }

}