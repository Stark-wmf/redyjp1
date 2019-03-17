package com.example.been;

import com.example.annotation.HttpMethod;

import java.util.Objects;

public class RouteInfo {

    private  String uri;
    private HttpMethod method;
    private String uriParam;

    public  String getUri(){ return uri;}

    public  HttpMethod getMethod(){return method;}

    String getUriParam(){return uriParam;}
    public void setUriParam(String uriParam) {
        this.uriParam = uriParam;
    }

    public RouteInfo(HttpMethod method,String uri){
        this.method=method;
        this.uri=uri;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RouteInfo routeInfo = (RouteInfo) o;
        return Objects.equals(uri, routeInfo.uri) &&
                method == routeInfo.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }

}
