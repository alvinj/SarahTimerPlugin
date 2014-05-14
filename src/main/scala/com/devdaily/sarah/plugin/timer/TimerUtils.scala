package com.devdaily.sarah.plugin.timer

import scala.concurrent.duration._
import java.util.regex.Pattern

object TimerUtils {
  
    // set timer for one second
    // set a timer for one second
    // set a timer for nine seconds
    // set a timer for nine minutes
    // set timer for 10 seconds
    // set timer for 10 minutes
    // set timer for 10 hours
    val patternWeHandle = ".*(set|create) a* *timer (to|for) (.*) (seconds*|minutes*|hours*).*"
    val compiledPattern = Pattern.compile(patternWeHandle)
    
    // the plugin should call this method
    def phraseMatchesOurPattern(spokenPhrase: String) = spokenPhrase.trim.toLowerCase.matches(patternWeHandle)
    
    // the plugin should call this method
    // get (a) the numerical time and (b) seconds/minutes/hours from the spoken phrase,
    // and create a scala.concurrent.duration.Duration from it
    def getDurationFromSpokenPhrase(spokenPhrase: String): Option[FiniteDuration] = {
        val m = compiledPattern.matcher(spokenPhrase)
        if (m.find) {
            val intOption = convertNumericStringToInt(m.group(3).trim)
            val timeUnitString = m.group(4).trim
            val timeUnitOption = durationMap.get(timeUnitString)
            for {
                i <- intOption
                tu <- timeUnitOption
            } yield FiniteDuration(i, tu)  // yields as an option
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






