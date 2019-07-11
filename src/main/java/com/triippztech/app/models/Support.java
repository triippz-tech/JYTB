
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

package com.triippztech.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Support {

    @SerializedName("https")
    @Expose
    private Integer https;
    @SerializedName("get")
    @Expose
    private Integer get;
    @SerializedName("post")
    @Expose
    private Integer post;
    @SerializedName("cookies")
    @Expose
    private Integer cookies;
    @SerializedName("referer")
    @Expose
    private Integer referer;
    @SerializedName("user_agent")
    @Expose
    private Integer userAgent;
    @SerializedName("google")
    @Expose
    private Integer google;

    public Integer getHttps() {
        return https;
    }

    public void setHttps(Integer https) {
        this.https = https;
    }

    public Integer getGet() {
        return get;
    }

    public void setGet(Integer get) {
        this.get = get;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getCookies() {
        return cookies;
    }

    public void setCookies(Integer cookies) {
        this.cookies = cookies;
    }

    public Integer getReferer() {
        return referer;
    }

    public void setReferer(Integer referer) {
        this.referer = referer;
    }

    public Integer getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(Integer userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getGoogle() {
        return google;
    }

    public void setGoogle(Integer google) {
        this.google = google;
    }

}
