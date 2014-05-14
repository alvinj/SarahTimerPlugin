package com.devdaily.sarah.plugin.timer

import com.devdaily.sarah.plugins.SarahPlugin
import com.devdaily.sarah.Sarah
import com.devdaily.sarah.plugins.PleaseSay
import akka.actor.ActorSystem
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.regex.Pattern

/**
 * Let the user say, "Set timer for ten minutes", "Cancel timer",
 * and similar.
 */
class TimerPlugin extends SarahPlugin {

    // used by any Future calls
    implicit val actorSystem = ActorSystem("CurrentTimeActorSystem")
      
    // tell sarah whether we can handle the given phrase (true) or not (false)
    def handlePhrase(spokenPhrase: String): Boolean = {
        if (TimerUtils.phraseMatchesOurPattern(spokenPhrase)) {
            val durationOption = TimerUtils.getDurationFromSpokenPhrase(spokenPhrase)
            durationOption match {
                case Some(duration) => 
                    brain ! PleaseSay("The timer has been set.")
                    actorSystem.scheduler.scheduleOnce(duration, brain, PleaseSay("Alert - This is a timer reminder."))
                    true
                case None =>
                    // it matched our pattern, but we couldn't extract the intValue and timeUnit for some reason
                    brain ! PleaseSay("Sorry, the Timer couldn't understand that.")
                    true
            }
        } else {
            false
        }
    }

    // nothing to do at startup
    def startPlugin = {}

    override def setPluginDirectory(dir: String) {
        // do nothing 
    }

    // TODO verify - i don't think Sarah uses this any more
    val phrasesICanHandle = List("foo bar")

    // sarah used to call this (may still, but it needs to go away)
    def textPhrasesICanHandle: List[String] = {
        return phrasesICanHandle
    }

}






