<p align="center"><img width=100% height=15% src="https://i.ibb.co/k26SXvt/jytbot2-1.jpg"></p>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Java](https://img.shields.io/badge/JDK-11+-blue.svg)
[![GitHub Issues](https://img.shields.io/github/issues/triippz-tech/JYTB.svg)](https://github.com/triippz-tech/JYTB/issues)
![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)
[![License](https://img.shields.io/badge/license-GPL-blue.svg)](https://opensource.org/licenses/GPL-3.0)

# Basic Overview

**Note:** *This project was made for educational purposes. I am not liable for anything you decide to do with this bot*. 

JYTBOT  is a multi-threaded Java application, which "views" a given YouTube video. The bot auto-rotates proxies if a proxy
has been determined to be dead, compromised, or the bot has finished watching the video. The goal is to provide unique 
views to a YouTube video.
<p align="center"><img width=100% height=35% src="https://i.ibb.co/7JxrxJy/ssJYTB.jpg"></p>

<br>

# Important
- Released only Trial Version (Limit some Features).
- Join Discord Group for full version : https://discord.gg/6Ax2Qbe
- Optimized for Chrome Driver. 


# Limitation on Trial Version
- only can use 1 worker.
- if Bot successful views 100 times,bot will terminated and cannot be used anymore.
- Cannot use Headless mode.
- for each views, Watch Duration only 40 Seconds.


# Features 
 - Using config file for setting up bot.
 - 3 Method Bot Options.
    1. Direct url to video page.
    2. Search using name of video.
    3. Search using medium keyword (Best option)
 - 3 proxy Options
    1. Free proxy using proxy scrapper by dev.
    2. paid proxy using premium pubproxy apikey.
    3. Manual proxy using proxylist.txt (u can use your own proxylist).
 - using chrome driver ( Firefox Deprecated ) .
 - include auto restart system if bot crash.
 - unlimited workers (thread) Processing.
 - All workers start when bot started.
 
# How to run this app

## on LINUX

- Download Java ( default-jdk)
- Download Chrome Browser.
```
  Make sure chrome location at /usr/bin/chrome
  if ur chrome have name chrome-browser  rename  to chrome 
```


- Download Chrome Driver 
```
https://chromedriver.chromium.org/downloads
- Make sure download driver match with your chrome version.
- move chromedriver file to /usr/local/bin/
```

- Download Bot file for linux on release tab.
- extract to some folder.
- Edit Config.properties.
- Run the bot.
```
chmod +x jytb.run
./jytb.run
```

### on Windows
- Download Chrome Browser
- Download Java 
  https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html
- Download Chrome Driver
  ```
  https://chromedriver.chromium.org/downloads
  - store chromedriver.exe to your chrome directory
  - make sure ur chrome directory is 
   C:\Program Files (x86)\Google\Chrome\Application   
  ``` 
- Download bot file from releases tab
- Extract to folder.
- Edit Config.properties.
- run JYTB.exe for start the bot.

## Screenshot App Run on 6 vps linux  
<p align="left"><img width=50% src="https://i.ibb.co/b3vKp6k/SS0594.jpg"></p>

## Screenshot Traffic from yt video analystic
<p align="left"><img width=50% src="https://i.ibb.co/rfZFKfK/SS0595.jpg"></p>

# Change-Log:
## Version 2.0

###  Added 
- 3 Options for Viewing methods.
- Headless options.
- Latest user agents for Firefox.

###  Bug Fixed
- Proxy for Firefox Drivers.

###  Improved
- Complex handling for error.
- Bot Detect if proxy down.
- Bot Detect if proxy need user login.
- Bot Detect if Proxy Detected.
- Bot Detect if Bot Detected by Youtube.

## Version 2.1

###  Added 
- Config file.
- Proxylist.txt .
- Chrome Driver support.
- Keyword Mode

###  Bug Fixed
- Wrong information about bot really watch video or not . 
- All workers not start at the same time.

### Removed
- Firefox Driver.
- IG url Mode.

###  Improved
- More Faster.
- keyword Mode can scroll down on video search list up to 150+ videos.
- can use your own proxy list.
- Bot setting using config file, so no need input setting on every bot start anymore.
