/*
 *     JYTBot, YouTube viewer bot for educational purposes
 *     Copyright (C) 2019  Mark Tripoli (triippztech.com)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.triippztech.app.services;

import com.triippztech.app.exceptions.NoSuchTitleException;
import com.triippztech.app.models.Constants;
import com.triippztech.app.models.Driver;
import com.triippztech.app.models.Proxies;
import com.triippztech.app.utils.AnsiColors;
import com.triippztech.app.utils.Log;
import com.triippztech.app.utils.UserAgent;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Stream;

public class BotWorker implements Runnable {

    private String workerName;
    private String workerColor;
    private String apiKey;
    private String videoUrl;
    private Driver driverType;
    private Integer watchLength;
    private Proxies proxies;
    private WebDriver webDriver;
    private File driverLocation;
    private UserAgent userAgent;
    private Integer numberOfWatches = 0;

    public BotWorker(String workerName, String apiKey, String videoUrl, Driver driverType,
                     Integer watchLength, String workerColor, File driverLocation)
            throws IOException
    {
        this.workerName = workerName;
        this.apiKey = apiKey;
        this.videoUrl = videoUrl;
        this.driverType = driverType;
        this.watchLength = watchLength;
        this.workerColor = workerColor;
        this.driverLocation = driverLocation;

        this.initializeBot();
    }

    @SuppressWarnings("Duplicates")
    private void initializeBot() throws IOException {
        Log.WINFO(this.workerName, this.workerColor, "Initializing. . .");
        this.proxies = new Proxies(this.workerName, this.apiKey, this.workerColor);
        this.userAgent = new UserAgent(this.driverType);
        switch (this.driverType.name() )
        {
            case Constants.FIREFOX:
                this.setFirefoxDriver();
                break;
            case Constants.CHROME:
                this.setChromeDriver();
                break;
            case Constants.SAFARI:
            case Constants.OPERA:
            case Constants.EDGE:
                break;
        }
        Log.WINFO(this.workerName, this.workerColor, "Initialization Complete!");
    }

    private void setChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        List<String> chromeOptions = new ArrayList<>();
        LoggingPreferences logPrefs = new LoggingPreferences();

        chromeOptions.add(String.format("--proxy-server=%s", proxies.getCurrentProxyModel().getIp()));
        chromeOptions.add(String.format("--user-agent=%s", userAgent.randomUA()));
        chromeOptions.add("--mute-audio");

        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        options.addArguments(chromeOptions);
        options.setBinary(this.driverLocation);
        options.setHeadless(true);
//        options.setProxy(this.proxies.getCurrentProxy());
        options.setCapability("proxy", this.proxies.getCurrentProxy());

        this.webDriver = new ChromeDriver(options);
        Log.WINFO(this.workerName, this.workerColor, "Chrome Driver Set.");
    }

    /**
     * Sets the webdriver to FireFox. We set our optimal parameters here
     * to ensure our proxy is set correctly.
     */
    private void setFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxBinary binary = new FirefoxBinary(this.driverLocation);
        LoggingPreferences logPrefs = new LoggingPreferences();


        profile.setPreference("media.volume_scale", "0.0");
        profile.setPreference("general.useragent.override", userAgent.randomUA());
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", this.proxies.getCurrentProxyModel().getIp());
        profile.setPreference("network.proxy.http_port", this.proxies.getCurrentProxyModel().getPort());
        profile.setPreference("network.proxy.ssl", this.proxies.getCurrentProxyModel().getIp());
        profile.setPreference("network.proxy.ssl_port", this.proxies.getCurrentProxyModel().getPort());

        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        options.setProfile(profile);
        options.setHeadless(true);
        options.setBinary(binary);
//        options.setProxy(this.proxies.getCurrentProxy());
        options.setCapability("proxy", this.proxies.getCurrentProxy());

        this.webDriver = new FirefoxDriver(options);
        Log.WINFO(this.workerName, this.workerColor, "Firefox Driver Set");
    }

    /**
     * Prior to watching the video, we need to set our worker up, with an accurate watch time. Meaning
     * We need to calculate where our current watch position is at in the video, use that current value
     * or how long we want to watch for, to determine how long our thread needs to sleep for (aka "watching").
     * @return watchTime (in millis)
     * @throws ElementClickInterceptedException
     * @throws NoSuchTitleException Our page doesnt end with YouTube, maybe there is an incorrect URL
     */
    private int setVideo() throws ElementClickInterceptedException, NoSuchTitleException {
        this.webDriver.get(this.videoUrl);

        if ( this.webDriver.getTitle().endsWith("YouTube") )
        {
            WebElement playButton = this.webDriver.findElement(By.className("ytp-play-button"));
            try {
                if (Objects.equals(playButton.getAttribute("title"), "Play (k)"))
                    playButton.click();
            } catch (ElementClickInterceptedException e) {
                throw new ElementClickInterceptedException(e.getMessage());
            }

            String currentVideoTime = this.webDriver.findElement(By.className("ytp-time-current")).getAttribute("innerHTML");
            String totalVideoTime = this.webDriver.findElement(By.className("ytp-time-duration")).getAttribute("innerHTML");
            if (this.watchLength == -1) {
                return calculateWatchTime( currentVideoTime, totalVideoTime );
            } else {
                return this.watchLength * 1000;
            }
        } else {
            throw new NoSuchTitleException(String.format("Title does not end with YouTube, please ensure you have " +
                    "provided the correct URL to the video. Actual title: %s", this.webDriver.getTitle()));
        }
    }

    /**
     * Utility to calculate how long our watch time is for. We calculate our current viewing position
     * (the current watch time in the video), and subtract that from the total length of the video.
     * Convert the seconds into millis for our Thread.Sleep
     * @param currentTime Current position our worker is at
     * @param videoLength The total lengtht of the video
     * @return watchTime in millis
     */
    private Integer calculateWatchTime ( String currentTime, String videoLength )
    {
        int[] curHMS =  Stream.of(currentTime.split(":")).mapToInt(Integer::parseInt).toArray();
        int[] totalHMS =  Stream.of(videoLength.split(":")).mapToInt(Integer::parseInt).toArray();
        int currentTimeSeconds;
        int totalTimeSeconds;
        if ( curHMS.length < 3 )
            currentTimeSeconds = (60 * curHMS[0]) + curHMS[1];
        else
            currentTimeSeconds = (3600 * curHMS[0]) + (60 * curHMS[1]) + curHMS[2];
        if ( totalHMS.length < 3 )
            totalTimeSeconds = (60 * totalHMS[0]) + totalHMS[1];
        else
            totalTimeSeconds = (3600 * totalHMS[0]) + (60 * totalHMS[1]) + totalHMS[2];

        int watchLength = totalTimeSeconds - currentTimeSeconds;
        return watchLength * 1000;
    }

    @SuppressWarnings("Duplicates")
    /**
     * Resets the workers(bot's) webDriver parameters, rotates our proxies to obtain a new
     * fresh IP, and ensures our last drivers process has been closed out.
     */
    private void resetBot()
    {
        Log.WINFO(workerName, workerColor, "Resetting Bot");
        if ( this.webDriver != null )
            this.webDriver.close();

        this.proxies.rotateProxies();

        switch (this.driverType.name() )
        {
            case Constants.FIREFOX:
                this.setFirefoxDriver();
                break;
            case Constants.CHROME:
                this.setChromeDriver();
                break;
            case Constants.SAFARI:
            case Constants.OPERA:
            case Constants.EDGE:
                break;
        }
    }

    /**
     * Provides a status message of our current worker
     */
    private void status()
    {
        StringBuilder builder = new StringBuilder()
                .append("\n")
                .append(String.format("  +------ %s %s Statistics %s ------+\n",
                        this.workerColor, this.workerName, AnsiColors.ANSI_RESET))
                .append(String.format("  [-] Current Video: %s%s%s\n",
                        AnsiColors.ANSI_BLUE, this.videoUrl, AnsiColors.ANSI_RESET))
                .append(String.format("  [-] Proxy IP: %s%s%s\n",
                        AnsiColors.ANSI_BLUE, this.proxies.getCurrentProxyModel().getIpPort(), AnsiColors.ANSI_RESET))
                .append(String.format("  [-] Proxies used: %s%d%s\n",
                        AnsiColors.ANSI_YELLOW, this.proxies.getUsedProxies().size(), AnsiColors.ANSI_RESET))
                .append(String.format("  [-] Visits: %s%d%s\n",
                        AnsiColors.ANSI_GREEN, this.numberOfWatches, AnsiColors.ANSI_RESET));
        System.out.println(builder.toString());
    }

    @Override
    public void run() {
        try {
            for (;;) {
                Thread.sleep(this.setVideo());
                Log.WINFO(workerName, workerColor,"Finished video");
                this.numberOfWatches += 1;
                this.status();
                this.resetBot();
            }
        } catch (InterruptedException e) {
            Log.WERROR(workerName, workerColor,e.getMessage());
            if ( this.webDriver != null ) {
                this.webDriver.close();
                this.webDriver.quit();
            }
        }

        Log.WINFO(workerName, workerColor,": Completed task");
    }
}
