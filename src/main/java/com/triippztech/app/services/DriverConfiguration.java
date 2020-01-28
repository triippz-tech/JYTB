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

import com.triippztech.app.models.Constants;
import com.triippztech.app.models.Driver;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;

public class DriverConfiguration {
    // macOS
    private final static String FIREFOX_MAC = "/Applications/Firefox.app/Contents/MacOS/firefox";
    private final static String CHROME_MAC = "/Applications/Google Chrome.app";
    private final static String OPERA_MAC = "/Applications/Opera.app";
    private final static String SAFARI_MAC = "/Applications/Safari.app";

    // WINDOWS
    private final static String FIREFOX_WIN_32 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    private final static String FIREFOX_WIN_64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    private final static String CHROME_WIN_32 = "";
    private final static String CHROME_WIN_64 = "";
    private final static String EDGE_WIN_64 = "";
    private final static String EDGE_WIN_32 = "";
    private final static String OPERA_WIN_64 = "";
    private final static String OPERA_WIN_32 = "";

    // LINUX

    @SuppressWarnings("Duplicates")
    public static void validateDriver(Driver driverType)
    {
        switch ( driverType.name() )
        {
            case Constants.FIREFOX:
                if (SystemUtils.IS_OS_MAC_OSX)
                    driverExists(FIREFOX_MAC);
                else if (SystemUtils.IS_OS_WINDOWS)
                    driverExists(FIREFOX_WIN_32, FIREFOX_WIN_64);
                else
                    //
                break;
            case Constants.CHROME:
                if (SystemUtils.IS_OS_MAC_OSX)
                    driverExists(CHROME_MAC);
                else if (SystemUtils.IS_OS_WINDOWS)
                    driverExists(CHROME_WIN_32, CHROME_WIN_64);
                else
                    //
                break;
            case Constants.SAFARI:
            case Constants.OPERA:
            case Constants.EDGE:
                break;
        }
    }

    private static Boolean driverExists(String location)
    {
        File file = new File(location);
        return file.exists();
    }

    private static Boolean driverExists(String location1, String location2)
    {
        File file = new File(location1);
        boolean exists =  file.exists();
        return exists;
    }

    public static File getDriver()
    {
        return new File(FIREFOX_WIN_64);
    }

//    public static File getFirefoxDriver()
//    {
//
//    }
//
//    public static File getChromeExe()
//    {
//
//    }
}
