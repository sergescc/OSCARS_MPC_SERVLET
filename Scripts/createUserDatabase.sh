#!/bin/bash

RESET_COLOR='\033[m'
BOLD_ORANGE='\033[1;31m'
THIN_AQUA='\033[36m'
THIN_BLUE='\033[34m'
THIN_BURGUNDY='\033[35m'
OK='\033[32m'
ERROR='\033[31m'

CLEAR_SCREEN='\033[2J'
TITLE_POSITION='\033[2;20H'
MENU_POS='\033[4;3H\033[s'
PROMPT_POSITION='\033[12;3H\033[s\033[K'
SUB_TAB='\033[u\033[2B\033[10C\033[s'
RESTORE_POS='\033[u'
ADD_SUB_TAB='\033[u\033[1B\033[s'
CLEAR_LINE='\033[K'
STATUS_LINE='\033[10;5H\033[K'
END_LINE='\033[14;0H'
OKSTATUS='\033[10;5H\033[K\033[32m STATUS: \033[m'

echo -en "$CLEAR_SCREEN"
echo -en "$TITLE_POSITION$BOLD_ORANGE Welcome to MPC Database Management\n $RESET_COLOR"
echo -en "$MENU_POS$THIN_AQUA What would you like to do: $RESET_COLOR"
echo -en "$SUB_TAB$THIN_BLUE 1.Create Databases $RESET_COLOR"
echo -en "$ADD_SUB_TAB$THIN_BLUE 2.Clear Databases $RESET_COLOR"
echo -en "$ADD_SUB_TAB$THIN_BLUE 3.Delete Databases $RESET_COLOR"
echo -en "$PROMPT_POSITION$THIN_BURGUNDY Command: $RESET_COLOR"
read COMMAND
if [ "$COMMAND" = "1" ]; then

	SQLRoot=root

	echo -en "$OKSTATUS Creating Database"
	if [ ! -z `which mysql` ]; then
	    	SQL=mysql
	elif [ ! -z `which mysql5` ]; then
    		SQL=mysql5
	else
    		echo mysql not found on path
    		echo "Please refer to GETTINGSTARTED Document"
    		exit -1
	fi

	sleep 1
	echo -en "$OKSTATUS Using $SQL"
	sleep 1
	echo -en "$OKSTATUS Please Enter Mysql root password"
	echo -en "$PROMPT_POSITION$THIN_BURGUNDY Password: $RESET_COLOR"
	read -s SQLpass

	if [ -z $SQLpass ]; then
		echo -en "$STATUS_LINE$ERROR WARNING: \033[s$RESET_COLOR Mysql root password is empty. \033[u\033[1B It is not secure to leave the password empty"
	fi
	
	$SQL -u $SQLRoot -p$SQLpass < 

elif [ "COMMAND" = "2" ]; then

	echo -en "$STATUS_LINE$OK STATUS: $RESET_COLOR Clearing Database"
                if [ ! -z `which mysql` ]; then
                SQL=mysql
        elif [ ! -z `which mysql5` ]; then
                SQL=mysql5
        else
                echo mysql not found on path
                echo "NO mySql Found Maybe It's all gone already"
        exit -1
        fi

elif [ "$COMMAND" = "3" ]; then

	echo -en "$STATUS_LINE$OK STATUS: $RESET_COLOR Deleting Database"
	        if [ ! -z `which mysql` ]; then
                SQL=mysql
        elif [ ! -z `which mysql5` ]; then
                SQL=mysql5
        else
                echo mysql not found on path
                echo "NO mySql Found Maybe It's all gone already"
        exit -1
        fi

else
	echo -en "$STATUS_LINE$ERROR ERROR: $RESET_COLOR Invalid Command"
fi

echo -en "$END_LINE"

exit 0
