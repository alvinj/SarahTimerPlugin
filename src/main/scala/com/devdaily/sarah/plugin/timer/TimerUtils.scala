package com.devdaily.sarah.plugin.timer

import scala.concurrent.duration._
import java.util.regex.Pattern

object TimerUtils {
  
    val patternWeHandle = ".*set timer (to|for) (.*) (seconds*|minutes*).*"
    val p = Pattern.compile(patternWeHandle)
      
    def phraseMatchesOurPattern(spokenPhrase: String) = spokenPhrase.trim.toLowerCase.matches(patternWeHandle)
    
    // get (a) the numerical time and (b) seconds/minutes/hours from the spoken phrase,
    // and create a scala.concurrent.duration.Duration from it
    def getDurationFromSpokenPhrase(spokenPhrase: String): Option[Duration] = {
        val m = p.matcher(spokenPhrase)
        if (m.find) {
            val intValue = convertNumericStringToInt(m.group(2).trim)
            val secondMinuteHourField = m.group(3).trim
            intValue match {
                case Some(i) => Some(Duration(i, durationMap.getOrElse(secondMinuteHourField, SECONDS)))
                case None => None
            }
        } else {
            None
        }
    }

    // this method expects to receive numbers less than ten spelled out, like "one", "two", etc.,
    // and numbers >= 10 as 10, 11, 12, etc.
    def convertNumericStringToInt(s: String): Option[Int] = {
        try {
            val i = s.toInt
            Some(i)
        } catch {
            case e: NumberFormatException =>
                val res = wordToNumberMap.get(s)
                res
        }
    }
    
    val durationMap = Map(
        "second" -> SECONDS,
        "seconds" -> SECONDS,
        "minute" -> MINUTES,
        "minutes" -> MINUTES,
        "hour" -> HOURS,
        "hours" -> HOURS
    )

    // for numbers < 10, mac speech recognition gives you text, like "one".
    // for numbers >= 10, it gives you numbers like "10".
    val wordToNumberMap = Map(
        "one" -> 1,
        "two" -> 2,
        "three" -> 3,
        "four" -> 4,
        "five" -> 5,
        "six" -> 6,
        "seven" -> 7,
        "eight" -> 8,
        "nine" -> 9
    )

}






