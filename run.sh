#!/bin/bash 
echo -e "\e[92mUPDATING SYSTEM" 
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
sudo apt update &&
echo -e "\e[92mInstalling Depedencies"
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
sudo apt -y  install default-jdk firefox &&
wget https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz &&
tar -xvzf geckodriver* &&
chmod +x geckodriver &&
mv geckodriver /usr/local/bin/ &&
wget https://github.com/triippz-tech/JYTB/releases/download/1.0.0/JYTB.jar &&
echo -e "\e[92mRunning the BOT"
read -p ".." -t 0.5 
read -p ".." -t 0.5
read -p ".." -t 0.5
echo -e "\e[0m"
exec java -jar JYTB.jar
