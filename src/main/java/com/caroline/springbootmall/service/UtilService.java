package com.caroline.springbootmall.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class UtilService {

    public String buildUrlPath(String HttpOrHttps, String baseHost, String path){

        String result = null;
        try {
            result =  new URIBuilder().setScheme(HttpOrHttps).setHost(baseHost).setPath(path).build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    //20180301 -> 2018-03-01
    public String formatDateString (String date, String mark){
        return String.join(mark , date.substring(0, 4), date.substring(4, 6), date.substring(6, 8));
    }
}
