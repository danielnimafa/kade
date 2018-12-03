package com.danielnimafa.footballclub.utils

import com.danielnimafa.footballclub.utils.UtilsStuff.toSimpleDateStr
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsStuffTest {

    @Test
    fun testToSimpleDateStr() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", toSimpleDateStr(date))
    }
}