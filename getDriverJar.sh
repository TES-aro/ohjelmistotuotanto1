#!/bin/bash
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-9.7.0.tar.gz
tar -xf mysql-connector-j-9.7.0.tar.gz
rm mysql-connector-j-9.7.0.tar.gz
echo "mysql-connector*/" >> .gitignore
