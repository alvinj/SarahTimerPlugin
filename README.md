
Sarah 'Google Search' Plugin
============================

This is a new "Google Search" plugin for Sarah (v2). 
Just say "Google Albert Einstein", and it will open that search query in your default browser.


Files
-----

The jar file built by this project needs to be copied to the Sarah plugins directory.
On my computer that directory is _/Users/al/Sarah/plugins/DDGoogleSearch_.

Files in that directory should be:

    GoogleSearch.info
    GoogleSearch.jar
    README.txt

The _GoogleSearch.info_ file currently contains these contents:

    main_class = com.devdaily.sarah.plugin.googlesearch.GoogleSearchPlugin
    plugin_name = Google Search


To-Do
-----

* Let the user configure their desired browser


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

    cp target/scala-2.10/googlesearch_2.10-0.1.jar GoogleSearch.jar

    ls -l GoogleSearch.jar

    echo ""
    echo "Created GoogleSearch.jar. Copy that file to /Users/al/Sarah/plugins/DDGoogleSearch, like this:"
    echo "cp GoogleSearch.jar /Users/al/Sarah/plugins/DDGoogleSearch"


Dependencies
------------

This plugin depends on:

* The Sarah2.jar file.
* The Akka/Scala actors. The actor version needs to be kept in sync with whatever actor version
  Sarah2 uses.

As mentioned above, I need to improve the process of requiring and using the Sarah2.jar file,
but that's more of a problem for the Sarah2 project than for this project. 









