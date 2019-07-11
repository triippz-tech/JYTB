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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**«
 * This class provides methods to build an URL String.
 *
 * @author Mark Tripoli
 */
public class UrlUtil {

    private static final String PAIR_SEPARATOR = "=";

    private static final String PARAM_SEPARATOR = "&";

    private static final char QUERY_STRING_SEPARATOR = '?';

    /**
     * Builds the URL String.
     *
     * @param baseUrl
     *            the base URL.
     * @param params
     *            the URL parameter.
     * @return the URL as String.
     */
    public static String buildUrlQuery(String baseUrl, Map<String, String> params) {
        if (baseUrl.isEmpty() || params.isEmpty()) {
            return baseUrl;
        } else {
            StringBuilder query = new StringBuilder(baseUrl);
            query.append(QUERY_STRING_SEPARATOR);

            for (String key : params.keySet()) {
                query.append(key);
                query.append(PAIR_SEPARATOR);
                query.append(encodeString(params.get(key)));
                query.append(PARAM_SEPARATOR);
            }

            return query.substring(0, query.length() - 1);
        }
    }

    private static String encodeString(String text) {
        try {
            return URLEncoder.encode(text, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
        }
        return text;
    }
}