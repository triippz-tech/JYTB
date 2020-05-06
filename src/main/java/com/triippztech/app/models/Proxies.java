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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.triippztech.app.dao.HttpRequestSender;
import com.triippztech.app.dao.RequestSender;
import com.triippztech.app.http.Request;
import com.triippztech.app.http.Response;
import com.triippztech.app.utils.Log;
import com.triippztech.app.utils.UrlUtil;
import org.openqa.selenium.Proxy;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;

public class Proxies {
    private final String protoType = "https";

    private PubProxies proxies;
    private Set<Datum> usedProxies;
    private String workerName;
    private String workerColor;
    private String apiKey;
    private Datum currentProxyModel;
    private Proxy currentProxy;

    private RequestSender sender;
    private Gson gson;

    public Proxies(String workerName, String apiKey, String workerColor) {
        this.workerName = workerName;
        this.apiKey = apiKey;
        this.workerColor = workerColor;
        this.usedProxies = new HashSet<>();

        this.sender = new HttpRequestSender();
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        this.generateProxies();
    }

    @SuppressWarnings("Duplicates")
    public void generateProxies()
    {
        HashMap<String, String> params = new HashMap<>();
        params.put("api", apiKey);
        params.put("limit", "20");
        params.put("https", "true");
        params.put("format", "json");

        Request request = new Request(UrlUtil.buildUrlQuery(Constants.SearchEndpoint, params));
        try {
            Response response = sender.sendRequest(request);
            proxies = gson.fromJson(response.getBody(), PubProxies.class);
        } catch (JsonSyntaxException | IOException e) {
            Log.WERROR(workerName, workerColor, e.getMessage());
            generateProxies();
        }

        // Set the first
        this.loadNewProxy();
    }

    public void rotateProxies()
    {
        Log.WWARN(workerName, workerColor,"Rotating proxies");

        // Add the current proxy to the used Set
        this.usedProxies.add(this.currentProxyModel);
        // Load a new one
        this.loadNewProxy();
    }

    private void refreshProxies()
    {
        this.proxies = null;
        this.generateProxies();
    }
    // fix randomproxy problem
    private void loadNewProxy() {
        Log.WWARN(workerName, workerColor,"Load new proxies");
        this.usedProxies.add(this.getCurrentProxyModel());
        Datum proxy = randomProxy();
            if ( isUsed(proxy) ) {
                try {
                    Log.WWARN(workerName, workerColor,"proxy already used");
                    Thread.sleep(3000);
                    Log.WWARN(workerName, workerColor,"Refreshing Proxy list...");
                    this.refreshProxies();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                this.setCurrentProxyModel(proxy);
                this.setCurrentProxy(proxy);
            }

    }



    private Boolean isUsed(Datum proxy)
    {
        return usedProxies.contains(proxy);
    }

    private Datum randomProxy() {
        return proxies.getData().get(new Random().nextInt(proxies.getData().size()));
    }

    public List<Datum> getProxies() {
        return proxies.getData();
    }

    public Set<Datum> getUsedProxies() {
        return usedProxies;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Datum getCurrentProxyModel() {
        return currentProxyModel;
    }

    public void setCurrentProxyModel(Datum currentProxyModel) {
        this.currentProxyModel = currentProxyModel;
    }

    public Proxy getCurrentProxy() {
        return currentProxy;
    }

    public void setCurrentProxy(Datum currentProxy) {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(currentProxy.getIpPort());
        proxy.setSslProxy(currentProxy.getIpPort());
    }
}
