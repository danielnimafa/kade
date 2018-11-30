package com.danielnimafa.klasemenliga.model

data class Favorite(val id: Long?, val matchId: String?, val matchName: String?, val matchDetail: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_NAME: String = "MATCH_NAME"
        const val MATCH_DETAIL: String = "MATCH_DETAIL"
    }
}