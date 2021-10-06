package com.URL.Shortener.URLShortener;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Service
public class URLService {

    Logger logger = Logger.getLogger(URLService.class.getName());

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private boolean urlIsValid(String url) {
        boolean urlValid;

        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        urlValid = urlValidator.isValid(url);

        logger.warning(String.valueOf(urlValid) + " --- ");
        return urlValid;

    }

    private String urlHashing(String url) {

        String urlHash;
        if (urlIsValid(url)) {

            urlHash = Hashing.murmur3_32_fixed().hashString(url, StandardCharsets.UTF_8).toString();

            return urlHash;
        }
        throw new RuntimeException("URL is invalid ! ---> " + urlIsValid(url) + url);
    }

    public String urlShort(String url) {

        String urlShort;
        urlShort = urlHashing(url);
        stringRedisTemplate.opsForValue().set(urlShort, url);
        return urlShort;
    }

    public String urlRetrieve(String urlShort) {

        String url;
        url = stringRedisTemplate.opsForValue().get(urlShort);

        if (url == null) {
            throw new RuntimeException("No URL for: " + urlShort);
        }
        return url;
    }

    public void urlDelete(String url) {

        String urlShort = urlHashing(url);

        if (!stringRedisTemplate.hasKey(urlShort)) {
            throw new RuntimeException(url + " is NOT exist !");
        }
        stringRedisTemplate.delete(urlShort);
    }


}