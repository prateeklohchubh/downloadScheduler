#!/bin/sh

# JARPATH= absolute path of the jar/java file to execute
# ARGUMENTS= Arguments or flags given to the java file
# 

OURJARPATH="Path to jar"
ARGUMENTS="Command line arguments go here"
echo $OURJARPATH $ARGUMENTS

#echo $PWD/scheduler.sh
#command to execute our downloadmanager
#java $OURJARPATH $ARGUMENTS

#unset all variables after use
unset OURJARPATH ARGUMENTS
