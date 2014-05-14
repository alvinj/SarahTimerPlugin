package com.devdaily.sarah.plugin.timer

import org.scalatest._
import scala.concurrent.duration._

class TimerUtilsTests extends FunSuite with Matchers {

    val p1 = "set timer for one second"
    val p2 = "set timer to two seconds"
    val tenSeconds = "set timer to 10 seconds"
    val fiveMinutesA = "set timer to five minutes"
    val fiveMinutesB = "set timer to five minutes please"
    val oneHour = "set timer to one hour"
    val twoHours = "set timer to two hours"

    test("test the conversion of good and bad numeric strings") {
        assert(TimerUtils.convertNumericStringToInt("") == None)
        assert(TimerUtils.convertNumericStringToInt("one") == Some(1))
        assert(TimerUtils.convertNumericStringToInt("nine") == Some(9))
        assert(TimerUtils.convertNumericStringToInt("10") == Some(10))
    }
    
    test("test 'one second' phrase") {
        val r = TimerUtils.getDurationFromSpokenPhrase(p1)
        runDurationTests(r, 1, SECONDS)
    }

    test("test 'two seconds' phrase") {
        val r = TimerUtils.getDurationFromSpokenPhrase(p2)
        runDurationTests(r, 2, SECONDS)
    }

    test("test 'ten seconds' phrase") {
        val result = TimerUtils.getDurationFromSpokenPhrase(tenSeconds)
        runDurationTests(result, 10, SECONDS)
    }
    
    def runDurationTests(durationOption: Option[Duration], expectedInt: Int, expectedTimeUnit: java.util.concurrent.TimeUnit) {
        durationOption match {
            case Some(d) => 
                assert(d.length == expectedInt)
                assert(d._2 == expectedTimeUnit)
            case None => fail
        }
    }

    test("test 'five minutes' phrases") {
        val r1 = TimerUtils.getDurationFromSpokenPhrase(fiveMinutesA)
        runDurationTests(r1, 5, MINUTES)
        val r2 = TimerUtils.getDurationFromSpokenPhrase(fiveMinutesB)
        runDurationTests(r2, 5, MINUTES)
    }

}









