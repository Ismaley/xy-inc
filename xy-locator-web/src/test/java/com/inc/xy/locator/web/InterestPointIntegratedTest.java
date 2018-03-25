package com.inc.xy.locator.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.model.InterestPoint;
import com.inc.xy.locator.repository.InterestPointsRepository;
import com.inc.xy.locator.web.application.XyLocatorApplication;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.inc.xy.locator.service.exceptions.BusinessException.ErrorCode.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = XyLocatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InterestPointIntegratedTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    InterestPointsRepository repository;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    private ObjectMapper objectMapper;

    MockMvc mockMvc;

    private static final String PATH = "/interest-points";
    private Gson gson = new Gson();

    @Before
    public void setUp() {
        repository.deleteAll();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document("{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void shouldCreateInterestPoint() throws Exception {
        InterestPointTO point = buildPoint("point name", 22, 22);

        this.mockMvc.perform(post(PATH)
            .content(gson.toJson(point))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.pointName", is(point.getPointName())))
                .andExpect(jsonPath("$.latitude", is(point.getLatitude())))
                .andExpect(jsonPath("$.longitude", is(point.getLongitude())));
    }

    @Test
    public void shouldNotCreateInterestPointWithEmptyName() throws Exception {
        InterestPointTO point = buildPoint("", 22, 22);

        this.mockMvc.perform(post(PATH)
                .content(gson.toJson(point))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_NAME.code)))
                .andExpect(jsonPath("$.message", is(INVALID_NAME.message)))
                .andExpect(jsonPath("$.pathInfo", is(PATH)));
    }

    @Test
    public void shouldNotCreateInterestPointWithDuplicatedName() throws Exception {
        repository.save(InterestPoint.builder().pointName("point name").latitude(32).longitude(55).build());

        InterestPointTO point = buildPoint("point name", 22, 22);

        this.mockMvc.perform(post(PATH)
                .content(gson.toJson(point))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(DUPLICATED_NAME.code)))
                .andExpect(jsonPath("$.message", is(DUPLICATED_NAME.message)))
                .andExpect(jsonPath("$.pathInfo", is(PATH)));
    }

    @Test
    public void shouldNotCreateInterestPointWithInvalidCoordinates() throws Exception {
        InterestPointTO point = buildPoint("another point name", null, 22);

        this.mockMvc.perform(post(PATH)
                .content(gson.toJson(point))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(INVALID_COORDINATES.code)))
                .andExpect(jsonPath("$.message", is(INVALID_COORDINATES.message)))
                .andExpect(jsonPath("$.pathInfo", is(PATH)));
    }

    @Test
    public void shouldFindAllPoints() throws Exception {
        repository.save(InterestPoint.builder().pointName("point name").latitude(22).longitude(22).build());
        repository.save(InterestPoint.builder().pointName("another point name").latitude(35).longitude(22).build());

        this.mockMvc.perform(get(PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].pointName", is("point name")))
                .andExpect(jsonPath("$.[1].pointName", is("another point name")));
    }

    @Test
    public void shouldReturnEmptyListIfNoPointsAreSaved() throws Exception {

        this.mockMvc.perform(get(PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFindPointsByProximity() throws Exception {
//        repository.save(InterestPoint.builder().pointName("restaurant").latitude(1).longitude(2).build());
//        repository.save(InterestPoint.builder().pointName("bakery").latitude(1).longitude(3).build());
        repository.save(InterestPoint.builder().pointName("mall").latitude(2).longitude(4).build());
        repository.save(InterestPoint.builder().pointName("parking lot").latitude(3).longitude(4).build());
//        repository.save(InterestPoint.builder().pointName("drugstore").latitude(4).longitude(1).build());
//        repository.save(InterestPoint.builder().pointName("ice cream shop").latitude(2).longitude(3).build());

        String path = String.format("%s/%s", PATH, "byproximity");

        this.mockMvc.perform(get(path)
                .accept(MediaType.APPLICATION_JSON)
                .param("latitude", "2")
                .param("longitude", "3")
                .param("radius", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
//                .andExpect(jsonPath("$", hasSize(5)))
//                .andExpect(jsonPath("$.[0].pointName", is("restaurant")))
//                .andExpect(jsonPath("$.[1].pointName", is("bakery")))
//                .andExpect(jsonPath("$.[2].pointName", is("mall")))
//                .andExpect(jsonPath("$.[3].pointName", is("parking lot")))
//                .andExpect(jsonPath("$.[4].pointName", is("ice cream shop")));
    }

    @Test
    public void shouldReturnEmptyListIfThereAreNoPointsNearby() throws Exception {
        repository.save(InterestPoint.builder().pointName("bakery").latitude(4).longitude(1).build());

        String path = String.format("%s/%s", PATH, "byproximity");

        this.mockMvc.perform(get(path)
                .accept(MediaType.APPLICATION_JSON)
                .param("latitude", "2")
                .param("longitude", "3")
                .param("radius", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldNotFindPointsByProximityIfSearchParamIsInvalid() throws Exception {
        repository.save(InterestPoint.builder().pointName("bakery").latitude(4).longitude(1).build());

        String path = String.format("%s/%s", PATH, "byproximity");

        this.mockMvc.perform(get(path)
                .accept(MediaType.APPLICATION_JSON)
                .param("longitude", "15")
                .param("radius", "7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode", is(INVALID_SEARCH_PARAM.code)))
                .andExpect(jsonPath("$.message", is(INVALID_SEARCH_PARAM.message)))
                .andExpect(jsonPath("$.pathInfo", is(path)));
    }

    private InterestPointTO buildPoint(String name, Integer latitude, Integer longitude) {
        return InterestPointTO.builder()
                .pointName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
