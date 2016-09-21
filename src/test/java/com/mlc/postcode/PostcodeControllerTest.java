package com.mlc.postcode;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PostcodeControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PostcodeController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(controller, "postcodeUrl", "http://postcodeUrl");
        ReflectionTestUtils.setField(controller, "geocodeUrl", "http://geocodeUrl");
    }

    @Test
    public void successFind() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("param1", "param1value");
        params.add("param2", "param2value");

        ResponseEntity<String> entity = new ResponseEntity<String>("[{\"result\":\"message\"}]", HttpStatus.OK);

        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(entity);

        controller.find("key", "lookup", "country", "code", params);

        verify(restTemplate, times(1)).exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class));
    }

    @Test
    public void successFindByReverseGeocode() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("param1", "param1value");
        params.add("param2", "param2value");

        ResponseEntity<String> entity = new ResponseEntity<String>("[{\"result\":\"message\"}]", HttpStatus.OK);

        when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(entity);

        controller.findByReverseGeocode("key", "country", "latitude", "longitude", params);

        verify(restTemplate, times(1)).exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), eq(String.class));
    }
}
