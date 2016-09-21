package com.mlc.postcode;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class PostcodeControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String baseContext = "/pcw/{apikey}";

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void byAddressIreland() throws Exception {
        mvc.perform(get(baseContext + "/{lookupType}/{country}/{code}", "PCW45-12345-12345-1234X", "address", "ie", "D02X285").param("lines", "3").param("format", "json")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].addressline2").value("29-31 Adelaide Road"));
    }

    @Test
    public void byReverseGeocode() throws Exception {
        mvc.perform(get(baseContext + "/{lookupType}/{country}/{latitude}/{longitude}", "PCW45-12345-12345-1234X", "rgeo", "ie", "53.332067", "-6.255492").param("distance", "50").param("format", "json")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].organisation").value("Irish Pension Trust"));
    }
}
