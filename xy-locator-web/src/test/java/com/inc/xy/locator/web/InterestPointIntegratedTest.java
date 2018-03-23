package com.inc.xy.locator.web;

import com.google.gson.Gson;
import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.repository.InterestPointsRepository;
import com.inc.xy.locator.web.application.XyLocatorApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XyLocatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterestPointIntegratedTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    InterestPointsRepository repository;

    private static final String PATH = "/interest-points";

    private Gson gson = new Gson();

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void shouldCreateInterestPoint() throws Exception {
        InterestPointTO point = buildPoint("point name", 22, 22);

        mockMvc.perform(post(PATH)
            .content(gson.toJson(point))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.pointName", is(point.getPointName())))
                .andExpect(jsonPath("$.xcoordinate", is(point.getXCoordinate())))
                .andExpect(jsonPath("$.ycoordinate", is(point.getXCoordinate())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldNotCreateInterestPointWithEmptyName() throws Exception {
        InterestPointTO point = buildPoint("", 22, 22);

        mockMvc.perform(post(PATH)
                .content(gson.toJson(point))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    private InterestPointTO buildPoint(String name, Integer xCoordinate, Integer yCoordinate) {
        return InterestPointTO.builder()
                .pointName(name)
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .build();
    }
}
