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

package com.triippztech.app;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.triippztech.app.dao.HttpRequestSender;
import com.triippztech.app.dao.RequestSender;
import com.triippztech.app.http.Request;
import com.triippztech.app.http.Response;
import com.triippztech.app.models.Constants;
import com.triippztech.app.models.Driver;
import com.triippztech.app.services.BotWorker;
import com.triippztech.app.services.DriverConfiguration;
import com.triippztech.app.utils.AnsiColors;
import com.triippztech.app.utils.UrlUtil;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * <p>App class.</p>
 *
 * @author tester
 * @version $Id: $Id
 */
public class App {
    private static String videoUrl;
    private static String apiKey;
    private static Driver driverType;
    private static String proxyType;
    private static Integer watchLength;
    private static Integer numberOfWorkers;

    private static final String[] schemes = { "http","https" };
    private static final UrlValidator urlValidator = new UrlValidator(schemes);


    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     * @throws java.io.IOException if any.
     */
    public static void main(String[] args) throws IOException
    {
        showWelcome();
        setVideoUrl();
        setProxyService();
        // setDriverType();
        setWatchLength();
        setNumberOfWorkers();
        clearScreen();


        ExecutorService executor = Executors.newFixedThreadPool(numberOfWorkers);
        for ( int i = 0; i < numberOfWorkers; i++)
        {
            Runnable worker = new BotWorker(
                    "Worker " + (i + 1),
                    apiKey,
                    videoUrl,
                    driverType,
                    watchLength,
                    AnsiColors.randomForeground(),
                    DriverConfiguration.getDriver(),
                    proxyType
                    );
            executor.execute(worker);
        }

        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) { }
        System.out.println("\nFinished all threads");
    }

    /**
     * <p>showWelcome.</p>
     *
     * @throws java.io.IOException if any.
     */
    public static void showWelcome() throws IOException {
        String display = FigletFont.convertOneLine("JYTBot");

        System.out.print(AnsiColors.ANSI_GREEN + display + AnsiColors.ANSI_RESET);
        System.out.println();
        System.out.println(AnsiColors.ANSI_YELLOW + "Author: Mark Tripoli" + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW + "Forked by: H4ckm3-id" + AnsiColors.ANSI_RESET);
        String version = "0.0.2";
        System.out.println(AnsiColors.ANSI_YELLOW + "Version: " + version + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW + "License: GNU GPL v3" + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW + "Main Repo: https://github.com/triippz-tech/JYTBot" + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_YELLOW + "Fork Repo: https://github.com/h4ckm3-id/JYTB.git" + AnsiColors.ANSI_RESET);
        System.out.println();
        System.out.println(AnsiColors.ANSI_BRIGHT_RED + "This application is meant for educational purposes only. " +
                "What you do with bot, is on you, I am not liable for anything you do with this."
                + AnsiColors.ANSI_RESET);
    }

    private static void setVideoUrl()
    {
        Scanner scanner = new Scanner(System.in);
        boolean validated = false;

        while (!validated)
        {
            System.out.print("What is the URL of the YouTube video?  -> ");
            String url = scanner.next();

            if (urlValidator.isValid(url)) {
                videoUrl = url;
                validated = true;
            } else {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid URL" + AnsiColors.ANSI_RESET);
            }
        }
    }

    private static void setProxyService()
    {
        boolean validated = false;

        while (!validated) {
            System.out.println("We using Proxy services from pubproxy.com");
            System.out.println("Free and PAID (Paid need API Key)");
            System.out.println("Which Proxy services would you like to use?");
            System.out.println("1. Free Pubproxy");
            System.out.println("2. Paid Pubproxy");
            System.out.print("->  ");

            try {
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        proxyType = "FREE";
                        setDriverType();
                        validated = true;
                        break;
                    case 2:
                        proxyType = "PAID";
                        setApiKey();
                        validated = true;
                        break;
                    default:
                        System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                        validated = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }

    }
    private static void setApiKey()
    {
        Scanner scanner = new Scanner(System.in);
        boolean validated = false;

        while (!validated) {
            System.out.print("Insert your pubproxy API Key, (Key Only).");
            System.out.print("Paste here ->  ");
            String key = scanner.next();

            if ( apiKeyValid(key) ) {
                apiKey = key;
                validated = true;
            } else {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid API Key" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }
        setDriverType();
    }

    private static void setDriverType()
    {
        boolean validated = false;

        while (!validated) {
            System.out.println("Which driver would you like to use? (Enter a number 1-2)");
            System.out.println("1. Firefox");
            System.out.println("2. Chrome");
            System.out.print("->  ");

            try {
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        driverType = Driver.FIREFOX;
                        validated = true;
                        break;
                    case 2:
                        driverType = Driver.CHROME;
                        validated = true;
                        break;
                    default:
                        System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                        validated = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }
    }

    private static void setWatchLength() {
        boolean validated = false;

        while (!validated) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like the bot to watch the FULL video or a set number of seconds? Select an item:");
            System.out.println("1. Full Length");
            System.out.println("2. Specific Time with auto Randomize");
            System.out.print("->  ");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        watchLength = -1;
                        validated = true;
                        break;
                    case 2:
                        setWatchSeconds();
                        validated = true;
                        break;
                    default:
                        System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                        validated = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Selection" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }
    }

    private static void setWatchSeconds() {
        boolean validated = false;

        while (!validated) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("How long would you like to watch the video for, IN SECONDS? (Must be greater than 30 seconds)");
            System.out.print("->  ");
            try {
                int seconds = scanner.nextInt();

                if (seconds < 30) {
                    System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Timeframe" + AnsiColors.ANSI_RESET);
                    validated = false;
                }
                else {
                    watchLength = seconds;
                    validated = true;
                }
            } catch (InputMismatchException e) {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Invalid Timeframe" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }
    }

    private static void setNumberOfWorkers()
    {
        boolean validated = false;

        while (!validated) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("How many workers would you like to use? (default = 3)");
            System.out.print("->  ");
            try {
                int workers = scanner.nextInt();
                if (workers < 1) {
                    System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Must have 1 more workers" + AnsiColors.ANSI_RESET);
                    validated = false;
                }
                else if (workers > 20) {
                    System.out.println(AnsiColors.ANSI_BRIGHT_YELLOW + "I hope you know what you're doing" + AnsiColors.ANSI_RESET);
                    validated = true;
                    numberOfWorkers = workers;
                } else {
                    validated = true;
                    numberOfWorkers = workers;
                }
            } catch (InputMismatchException e) {
                System.out.println(AnsiColors.ANSI_BRIGHT_RED + "Must have 1 more workers" + AnsiColors.ANSI_RESET);
                validated = false;
            }
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @SuppressWarnings("Duplicates")
    private static Boolean apiKeyValid(String apiKey)
    {
        RequestSender sender= new HttpRequestSender();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap<String, String> params = new HashMap<>();

        params.put("api", apiKey);

        Request request = new Request(UrlUtil.buildUrlQuery(Constants.SearchEndpoint, params));
        try {
            Response response = sender.sendRequest(request);
            return !response.getBody().contains("Invalid API");
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
    }
}
