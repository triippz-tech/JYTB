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


import com.triippztech.app.models.Driver;
import java.io.*;
import java.util.*;

public class UserAgent {
    private Driver driverType;
    private List<String> userAgentList;


    public UserAgent(Driver driverType) throws IOException {
        this.driverType = driverType;
        // Read user Agents data, working on Jar or debug on IDEA
        InputStream inputStream = getClass().getResourceAsStream("/userAgents/" + driverType.toString() + ".txt");
        InputStreamReader streamReader = new InputStreamReader (inputStream);
        BufferedReader reader = new BufferedReader (streamReader);
        ArrayList<String> temp = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null)
        {
            temp.add(line);
        }
        this.userAgentList = temp;
    }

    public String randomUA() {
        return this.userAgentList.get(new Random().nextInt(this.userAgentList.size()));
    }
}
