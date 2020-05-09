
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

public class Datum {

    @SerializedName("ipPort")
    @Expose
    private String ipPort;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("last_checked")
    @Expose
    private String lastChecked;
    @SerializedName("proxy_level")
    @Expose
    private String proxyLevel;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("support")
    @Expose
    private Support support;

    @SerializedName("google_error")
    @Expose
    private String google_error;

    @SerializedName("google_status")
    @Expose
    private String google_status;

    @SerializedName("google_total_time")
    @Expose
    private String google_total_time;

    @SerializedName("yahoo_error")
    @Expose
    private String yahoo_error;

    @SerializedName("yahoo_status")
    @Expose
    private String yahoo_status;

    @SerializedName("yahoo_total_time")
    @Expose
    private String yahoo_total_time;

    @SerializedName("yandex_error")
    @Expose
    private String yandex_error;

    @SerializedName("yandex_status")
    @Expose
    private String yandex_status;

    @SerializedName("yandex_total_time")
    @Expose
    private String yandex_total_time;


    public String getIpPort() {
        String ip = this.ip;
        String port = this.port;
        return ip + ":" + port;
    }

    public void setIpPort(String Port, String ip) {
        this.ipPort = ip + ":" + port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(String lastChecked) {
        this.lastChecked = lastChecked;
    }

    public String getProxyLevel() {
        return proxyLevel;
    }

    public void setProxyLevel(String proxyLevel) {
        this.proxyLevel = proxyLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public String getGoogle_error() {
        return google_error;
    }

    public void setGoogle_error(String google_error) {
        this.google_error = google_error;
    }

    public String getGoogle_status() {
        return google_status;
    }

    public void setGoogle_status(String google_status) {
        this.google_status = google_status;
    }

    public String getGoogle_total_time() {
        return google_total_time;
    }

    public void setGoogle_total_time(String google_total_time) {
        this.google_total_time = google_total_time;
    }

    public String getYahoo_error() {
        return yahoo_error;
    }

    public void setYahoo_error(String yahoo_error) {
        this.yahoo_error = yahoo_error;
    }

    public String getYahoo_status() {
        return yahoo_status;
    }

    public void setYahoo_status(String yahoo_status) {
        this.yahoo_status = yahoo_status;
    }

    public String getYahoo_total_time() {
        return yahoo_total_time;
    }

    public void setYahoo_total_time(String yahoo_total_time) {
        this.yahoo_total_time = yahoo_total_time;
    }

    public String getYandex_error() {
        return yandex_error;
    }

    public void setYandex_error(String yandex_error) {
        this.yandex_error = yandex_error;
    }

    public String getYandex_status() {
        return yandex_status;
    }

    public void setYandex_status(String yandex_status) {
        this.yandex_status = yandex_status;
    }

    public String getYandex_total_time() {
        return yandex_total_time;
    }

    public void setYandex_total_time(String yandex_total_time) {
        this.yandex_total_time = yandex_total_time;
    }
}
