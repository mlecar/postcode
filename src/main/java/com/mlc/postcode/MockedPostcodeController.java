package com.mlc.postcode;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Validated
@RequestMapping(value = { "/mocked/pcw/{apikey}/", "/mocked/pcw/{apikey}" })
public class MockedPostcodeController {

    @Autowired
    private String postcodeUrl;

    @Autowired
    private String geocodeUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper mapper;

    @ResponseBody
    @RequestMapping(value = { "/{lookupType}/{country}/{code}", "/{lookupType}/{country}/{code}/" }, method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public String find(@PathVariable @NotNull String apikey, @PathVariable @NotNull String lookupType, @PathVariable @NotNull String country, @PathVariable @NotNull String code, @RequestParam MultiValueMap<String, String> params) throws Exception {

        Resource resource = null;
        switch (lookupType) {
        case "address":
            resource = resourceLoader.getResource("classpath:mocked-postcoder-responses/by.address." + country + ".json");
            break;
        case "addressgeo":
            resource = resourceLoader.getResource("classpath:mocked-postcoder-responses/by.addressgeo.json");
            break;
        case "position":
            resource = resourceLoader.getResource("classpath:mocked-postcoder-responses/by.position.json");
            break;
        default:
            return "";
        }

        JsonNode node = mapper.readTree(resource.getInputStream());

        return node.toString();
    }

    @Cacheable("postcodes")
    @ResponseBody
    @RequestMapping(value = { "/{lookupType}/{country}/{latitude}/{longitude:.+}", "/{lookupType}/{country}/{latitude}/{longitude:.+}/" }, method = RequestMethod.GET, produces = { "application/json", "application/xml" })
    public String reverseGeocode(@PathVariable @NotNull String apikey, @PathVariable @NotNull String lookupType, @PathVariable @NotNull String country, @PathVariable @NotNull String latitude, @PathVariable @NotNull String longitude,
            @RequestParam MultiValueMap<String, String> params) throws Exception {

        Resource resource = resourceLoader.getResource("classpath:mocked-postcoder-responses/by.reversegeo.json");
        JsonNode node = mapper.readTree(resource.getInputStream());

        return node.toString();
    }
}
