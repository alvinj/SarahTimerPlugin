package com.devdaily.sarah.plugin.timer

object TimerTests extends App {

    val p1 = "set timer for 10 seconds"
    val p2 = "set timer to 10 seconds"
    val p3 = "set timer to 5 minutes"
    val p4 = "set timer to 5 minutes please"

    val pattern = ".*set timer .* (seconds|minutes).*"
    println(p1.matches(pattern))
    println(p2.matches(pattern))
    println(p3.matches(pattern))
    println(p4.matches(pattern))

}