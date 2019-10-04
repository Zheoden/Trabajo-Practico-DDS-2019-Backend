#!/bin/bash

HELP="Usage $(basename "$0") -- Script for fast hardcoded database and user creation for testing in this project. Use it after mysql/mariadb installation.
Parameters:
    -p  MySql/MariaDB root password [optional]
    -h  script help [optional]"

while getopts ":hp:" opt; do
  case $opt in
    h) echo "$HELP" >&2
       exit
       ;;
    p)
      ROOT_PASS=$OPTARG
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      echo "$HELP" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      echo "$HELP" >&2
      exit 1
      ;;
  esac
done

### SCRIPT true logic begin here ###
sudo mysql -u root $ROOT_PASS -e "create database 2018VnGroup05;CREATE USER '2018-vn-group-05'@'localhost' IDENTIFIED BY 'password';GRANT ALL PRIVILEGES ON 2018VnGroup05.* TO '2018-vn-group-05'@'localhost';"




