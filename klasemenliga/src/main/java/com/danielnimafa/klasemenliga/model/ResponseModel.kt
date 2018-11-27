package com.danielnimafa.klasemenliga.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class EventMatch(var events : List<MatchData>?)

@Parcelize
data class MatchData(var idEvent: String?,
                     var strEvent: String?,
                     var idSoccerXML: String?,
                     var idLeague: String?,
                     var strLeague: String?,
                     var intHomeShots: String?,
                     var intHomeScore: String?,
                     var strHomeGoalDetails: String?,
                     var strHomeLineupGoalkeeper: String?,
                     var strHomeLineupDefense: String?,
                     var strHomeLineupMidfield: String?,
                     var strHomeLineupForward: String?,
                     var strHomeLineupSubstitutes: String?,
                     var intAwayScore: String?,
                     var strAwayGoalDetails: String?,
                     var intAwayShots: String?,
                     var strAwayLineupGoalkeeper: String?,
                     var strAwayLineupDefense: String?,
                     var strAwayLineupMidfield: String?,
                     var strAwayLineupForward: String?,
                     var strAwayLineupSubstitutes: String?,
                     var dateEvent: String?,
                     var strDate: String?,
                     var strTime: String?) : Parcelable