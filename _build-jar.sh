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

