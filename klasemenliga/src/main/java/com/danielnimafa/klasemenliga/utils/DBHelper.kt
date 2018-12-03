package com.danielnimafa.klasemenliga.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.danielnimafa.klasemenliga.model.Favorite
import org.jetbrains.anko.db.*

class DBOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavTeam.db", null, 1) {

    companion object {
        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper {
            if (instance == null) {
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.MATCH_ID to TEXT + UNIQUE,
                Favorite.MATCH_NAME to TEXT,
                Favorite.MATCH_DETAIL to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.dbhelper: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)