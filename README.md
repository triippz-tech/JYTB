<p align="center"><img width=100% height=15% src="https://triippz-tech.github.io/JYTB/images/jytb-logo-wide.png"></p>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Java](https://img.shields.io/badge/JDK-11+-blue.svg)
[![GitHub Issues](https://img.shields.io/github/issues/triippz-tech/JYTB.svg)](https://github.com/triippz-tech/JYTB/issues)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](https://opensource.org/licenses/GPL-3.0)

## Basic Overview

**Note:** *This project was made for educational purposes. I am not liable for anything you decide to do with this bot*. 

JYTB is a multi-threaded Java application, which "views" a given YouTube video. The bot auto-rotates proxies if a proxy
has been determined to be dead, compromised, or the bot has finished watching the video. The goal is to provide unique 
views to a YouTube video.
<p align="center"><img width=95% src="https://triippz-tech.github.io/JYTB/images/run-example.png"></p>

<br>

## Current State
The current state of this project is "In Development". The core features of the bot are functional, but there are bugs
and issues which still need to be ironed out. The project should have it's first initial release sometime in July.

## Running the App
TDB - Will update upon first release

## Contributing
Please take a look at our [contributing](https://github.com/triippz-tech/JYTB/blob/master/docs/CONTRIBUTING.md) guidelines if you're interested in helping!
#### Pending Features
- Free Proxy Options
- Paid Proxy Options
- Headless or Head Mode
- Platform Independence
- Auto-installation of proper drivers
- Support for major browsers
    - Firefox
    - Chrome
    - Opera
    - Edge
    - Safari
- User-determined watch lengths for bots

### Running fork vesion by h4ckm3-id on linux ubuntu with command line (not packaged version)
- sudo apt update
- sudo apt install default-jdk
- sudo apt install maven
- sudo apt install firefox
- wget https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz
- tar -xvzf geckodriver*
- chmod +x geckodriver
- mv geckodriver /usr/local/bin/
- git clone -b JYTB-Linux-Firefox https://github.com/h4ckm3-id/JYTB.git bot
- cd bot
- mvn compile
- mvn exec:java -Dexec.mainClass="com.triippztech.app.App"
- paste url video
- if you using paid options, paste APIKEY pubproxy
- choose firefox for driver
- choose duration
- choose threads
- done 

 
