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

import com.triippztech.app.models.Constants;
import com.triippztech.app.models.Driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UserAgent {
    private Driver driverType;
    private List<String> userAgentList;

    public UserAgent(Driver driverType) throws IOException {
        this.driverType = driverType;
        this.userAgentList = Files.readAllLines(
                Paths.get(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("userAgents/" + driverType.toString() + ".txt")).getPath()));
    }

    public String randomUA() {
        return this.userAgentList.get(new Random().nextInt(this.userAgentList.size()));
    }
}
