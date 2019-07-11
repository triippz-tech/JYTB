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

package com.triippztech.app.http;

import java.util.Map;

/**
 * This class represents a response.
 *
 * @author Mark Tripoli
 */
public class Response {

    private Map<String, String> headers;
    private String body;

    /**
     * Constructs a new response.
     *
     * @param headers
     *            the header map
     * @param body
     *            the body.
     */
    public Response(Map<String, String> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    /**
     * Returns the header map.
     *
     * @return the header map
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Returns the body.
     *
     * @return the body.
     */
    public String getBody() {
        return body;
    }

}

