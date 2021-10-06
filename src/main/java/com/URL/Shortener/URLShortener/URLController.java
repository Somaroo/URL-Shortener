package com.URL.Shortener.URLShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller

// http://localhost:8080/urlshort
@RestController
@RequestMapping("/urlshort")
public class URLController {

    @Autowired
    private URLService urlService;

    // Post Http Method
    @PostMapping

    public String urlGetShort(@RequestBody String url) {

        String urlShort;
        urlShort = urlService.urlShort(url);

        return urlShort;
    }


    // get Http Method
    @GetMapping(value = "/{urlShort}")
    @ResponseBody
    public String getUrl(@PathVariable String urlShort){

        String url;
        url = urlService.urlRetrieve(urlShort);
        return url;
    }
}