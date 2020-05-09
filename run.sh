#!/bin/bash 
echo -e "\e[92mUPDATING SYSTEM" 
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
sudo apt update &&
echo -e "\e[92mInstalling all Depencies"
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
sudo apt -y  install default-jdk maven firefox &&
wget https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz &&
tar -xvzf geckodriver* &&
chmod +x geckodriver &&
mv geckodriver /usr/local/bin/ &&
git clone -b JYTB-Linux-Firefox https://github.com/h4ckm3-id/JYTB.git bot &&
echo -e "\e[92mRunning the BOT"
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
cd bot && exec mvn compile exec:java -Dexec.mainClass="com.triippztech.app.App"
