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

package com.triippztech.app.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("Duplicates")
public class Log {
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public static void WINFO(String workerName, String workerColor, String message){
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date())).append(" - ");

        builder.append(AnsiColors.ANSI_BRIGHT_GREEN);
        builder.append("[").append("INFO").append("]").append(" - ");
        builder.append(AnsiColors.ANSI_RESET);

        builder.append(workerColor);
        builder.append("[").append(workerName).append("]");
        builder.append(" - ");
        builder.append(message).append(AnsiColors.ANSI_RESET);
        System.out.println(builder.toString());
    }
    public static void WWARN(String workerName, String workerColor, String message) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date())).append(" - ");

        builder.append(AnsiColors.ANSI_YELLOW);
        builder.append("[").append("WWARN").append("]").append(" - ");
        builder.append(AnsiColors.ANSI_RESET);

        builder.append(workerColor);
        builder.append("[").append(workerName).append("]");
        builder.append(" - ");
        builder.append(message).append(AnsiColors.ANSI_RESET);
        System.out.println(builder.toString());
    }
    public static void WERROR(String workerName, String workerColor, String message) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date())).append(" - ");

        builder.append(AnsiColors.ANSI_BRIGHT_RED);
        builder.append("[").append("ERROR").append("]").append(" - ");
        builder.append(AnsiColors.ANSI_RESET);


        builder.append(workerColor);
        builder.append("[").append(workerName).append("]");
        builder.append(" - ");
        builder.append(message).append(AnsiColors.ANSI_RESET);
        System.out.println(builder.toString());
    }
    public static void WFATAL(String workerName, String workerColor, String message) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append(df.format(new Date())).append(" - ");

        builder.append(AnsiColors.ANSI_BG_RED);
        builder.append("[").append("WFATAL").append("]");
        builder.append(AnsiColors.ANSI_RESET).append(" - ");

        builder.append(workerColor);
        builder.append("[").append(workerName).append("]");
        builder.append(" - ");
        builder.append(message).append(AnsiColors.ANSI_RESET);
        System.out.println(builder.toString());
    }
}
