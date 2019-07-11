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
import com.triippztech.app.models.Constants;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides methods to send request to the web.
 *
 * @author Mark Tripoli
 */
public class HttpRequestSender implements RequestSender {

    @Override
    public Response sendRequest(Request request) throws IOException {

        HttpURLConnection connection = createConnection(request);

        return send(connection);
    }

    private HttpURLConnection createConnection(Request request) throws IOException {
        URL url = new URL(request.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(Constants.TIMEOUT);
        connection.setReadTimeout(Constants.TIMEOUT);

        connection.setRequestMethod(Constants.SEND_METHOD);

        return connection;
    }

    private Response send(HttpURLConnection connection) throws IOException {
        connection.connect();

        if (connection.getResponseCode() != 200) {
            throw new IOException("Bad response! Code: " + connection.getResponseCode());
        }

        Map<String, String> headers = new HashMap<>();
        for (String key : connection.getHeaderFields().keySet()) {
            headers.put(key, connection.getHeaderFields().get(key).get(0));
        }

        String body;

        try (InputStream inputStream = connection.getInputStream()) {

            String encoding = connection.getContentEncoding();
            encoding = encoding == null ? Constants.ENCODING : encoding;

            body = IOUtils.toString(inputStream, encoding);
        } catch (IOException e) {
            throw new IOException(e);
        }

        if (body == null) {
            throw new IOException("Unparseable response body! \n {" + body + "}");
        }

        return new Response(headers, body);
    }

}