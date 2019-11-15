package com.knmz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author zl
 * @date 2019/8/23 15:40
 */
public class RedisConfiguration {
    @JsonProperty
    private String host;

    @JsonProperty
    private int port;

    @JsonProperty
    private int minIdle;

    @JsonProperty
    private int maxIdle;

    @JsonProperty
    private int maxTotal;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }
}
