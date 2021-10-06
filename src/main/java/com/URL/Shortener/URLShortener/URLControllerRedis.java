package com.URL.Shortener.URLShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller

// http://localhost:8080/urlshort
@RestController
@RequestMapping("/urlshort")
public class URLControllerRedis {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private URLServiceRedis urlServiceRedis;

    // Post Http Method
    @PostMapping
    public String urlGetShort(@RequestBody String url) {

        String urlShort;
        urlShort = urlServiceRedis.urlShort(url);

        return urlShort;
    }

    // get Http Method
    @GetMapping()
    public String getUrl(@RequestBody String urlShort){

        String url;
        url = urlServiceRedis.urlRetrieve(urlShort);
        return url;
    }

    // Delete Http Method
    @DeleteMapping()
    public void deleteUrl(@RequestBody String url){

        urlServiceRedis.urlDelete(url);

    }
}
