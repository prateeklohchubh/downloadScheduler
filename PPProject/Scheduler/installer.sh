#!/bin/sh

# This script createds and Schedules a Task
#  minute(s) hour(s) day(s) month(s) weekday(s) command(s) format of scheduled task
# remove a crontab write crontab -r -i or run make cronclear
echo "0 0 * * * $PWD/scheduler.sh" > cronfile.txt

crontab cronfile.txt
