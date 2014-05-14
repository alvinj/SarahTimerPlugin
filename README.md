Sarah 'Timer' Plugin
====================

This is a new "Timer" plugin for Sarah (v2).
It can handle phrases like these:

    val oneSecond = "set timer for one second"
    val twoSeconds = "set timer to two seconds"
    val tenSeconds = "set timer to 10 seconds"
    val fiveMinutesA = "set timer to five minutes"
    val fiveMinutesB = "set timer to five minutes please"
    val tenMinutes = "set a timer to 10 minutes please"
    val oneHour = "set timer to one hour"
    val twoHours = "set timer to two hours"


Files
-----

The jar file built by this project needs to be copied to the Sarah plugins directory.
On my computer that directory is _/Users/al/Sarah/plugins/DDTimer_.

Files in that directory should be:

    Timer.info
    Timer.jar

The _Timer.info_ file currently contains these contents:

    main_class = com.devdaily.sarah.plugin.timer.TimerPlugin
    plugin_name = Timer


Developers - Building this Plugin
---------------------------------

You can build this plugin using the shell script named _build-jar.sh. It currently looks like this:

    #!/bin/bash

    sbt package

    if [ $? != 0 ]
    then
        echo "'sbt package' failed, exiting now"
        exit 1
    fi

    cp target/scala-2.10/timer_2.10-0.1.jar Timer.jar

    ls -l Timer.jar

    echo ""
    echo "Created Timer.jar. Copy that file to /Users/al/Sarah/plugins/DDTimer, like this:"
    echo "cp Timer.jar /Users/al/Sarah/plugins/DDTimer"


Dependencies
------------

This plugin depends on:

* The Sarah2.jar file.
* The Akka/Scala actors. The actor version needs to be kept in sync with whatever actor version
  Sarah2 uses.
* ScalaTest

As mentioned above, I need to improve the process of requiring and using the Sarah2.jar file,
but that's more of a problem for the Sarah2 project than for this project. 









