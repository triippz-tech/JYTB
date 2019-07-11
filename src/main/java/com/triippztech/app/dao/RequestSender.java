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

package com.triippztech.app.dao;

import com.triippztech.app.http.Request;
import com.triippztech.app.http.Response;

import java.io.IOException;

/**
 * An RequestSender is able to send a request by passing the URL to it. After
 * processing the request a response is returned.
 *
 * @author Mark Tripoli
 */
public interface RequestSender {

    /**
     * Sends the request and returns the received response.
     *
     * @param request
     *            the request which will be send
     * @return the received response
     * @throws IOException
     *             if an error occurred
     */
    Response sendRequest(Request request) throws IOException;

}

