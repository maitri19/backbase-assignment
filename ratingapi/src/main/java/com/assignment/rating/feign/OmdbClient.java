package com.assignment.rating.feign;

import com.assignment.rating.dto.OMDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "omdbclient", url = "${omdb.api.url}")
public interface OmdbClient {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    OMDBResponse getOmdbDetails(@RequestParam(value="t") String movieName, @RequestParam(value="apikey") String apikey);

}
