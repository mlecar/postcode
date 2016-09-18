package com.mlc.postcode;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Validated
@RequestMapping(value = { "/pcw/{apikey}/", "/pcw/{apikey}" })
public class PostcodeController {

    @Autowired
    private String postcoderUrl;

    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping(value = { "/{lookupType}/{country}/{code}", "/{lookupType}/{country}/{code}" }, method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public String find(@PathVariable @NotNull String apikey, @PathVariable @NotNull String lookupType, @PathVariable @NotNull String country, @PathVariable @NotNull String code, @RequestParam MultiValueMap<String, String> params) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("apikey", apikey);
        map.put("lookupType", lookupType);
        map.put("country", country);
        map.put("code", code);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(postcoderUrl).queryParams(params);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().expand(map).encode().toUri(), HttpMethod.GET, new HttpEntity<HttpHeaders>(headers), String.class);

        return responseEntity.getBody();
    }
}