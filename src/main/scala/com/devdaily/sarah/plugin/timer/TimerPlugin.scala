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
    def handlePhrase(phrase: String): Boolean = {
        if (TimerUtils.phraseMatchesOurPattern(phrase)) {
            brain ! PleaseSay("Okay, the timer has been set.")
            actorSystem.scheduler.scheduleOnce(10 seconds, brain, PleaseSay("This is a timer reminder."))
            true
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






