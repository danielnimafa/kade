package com.danielnimafa.footballclub.model

import android.os.Parcel
import android.os.Parcelable

data class FootballClub(var label: String,
                        var img: Int,
                        var desc: String)

class FootballClubParcel() : Parcelable {
    var label: String = ""
    var desc: String = ""
    var img: Int = 0

    constructor(parcel: Parcel) : this() {
        label = parcel.readString()
        desc = parcel.readString()
        img = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(label)
        parcel.writeString(desc)
        parcel.writeInt(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FootballClubParcel> {
        override fun createFromParcel(parcel: Parcel): FootballClubParcel {
            return FootballClubParcel(parcel)
        }

        override fun newArray(size: Int): Array<FootballClubParcel?> {
            return arrayOfNulls(size)
        }
    }
}