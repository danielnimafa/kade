package com.danielnimafa.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class EventMatch(var events : List<MatchData>?)

@Parcelize
data class MatchData(var idEvent: String?,
                     var strEvent: String?,
                     var idSoccerXML: String?,
                     var idLeague: String?,
                     var strLeague: String?,
                     var idHomeTeam: String?,
                     var intHomeShots: String?,
                     var intHomeScore: String?,
                     var strHomeTeam: String?,
                     var strHomeGoalDetails: String?,
                     var strHomeLineupGoalkeeper: String?,
                     var strHomeLineupDefense: String?,
                     var strHomeLineupMidfield: String?,
                     var strHomeLineupForward: String?,
                     var strHomeLineupSubstitutes: String?,
                     var intAwayScore: String?,
                     var strAwayGoalDetails: String?,
                     var intAwayShots: String?,
                     var idAwayTeam: String?,
                     var strAwayTeam: String?,
                     var strAwayLineupGoalkeeper: String?,
                     var strAwayLineupDefense: String?,
                     var strAwayLineupMidfield: String?,
                     var strAwayLineupForward: String?,
                     var strAwayLineupSubstitutes: String?,
                     var dateEvent: String?,
                     var strDate: String?,
                     var strTime: String?) : Parcelable

data class TeamList(var teams: List<TeamDetail>)

@Parcelize
data class TeamDetail(var idTeam: String,
                      var strTeam: String,
                      @SerializedName("strTeamBadge")
                      var strTeamLogo: String?) : Parcelable

data class Team(
        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeam")
        var teamName: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,

        @SerializedName("strStadium")
        var teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        var teamDescription: String? = null
)

data class TeamResponse(
        val teams: List<Team>)