/**
 * Copyright (c) 2023, Indunil Rathnayake. All Rights Reserved.
 */
package com.cognizant.rover.navigation.api;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test cases for testing the rover navigation API
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RoverNavigationApiTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testRoverNavigation() throws Exception {

        mvc.perform(post("/api/grid")
                        .content("{\"command\": \"5 5\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult result_1 = mvc.perform(post("/api/rover")
                        .content("{\"command\": \"1 2 N\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("1 2 N"))
                .andReturn();
        String roverID_1 = JsonPath.read(result_1.getResponse().getContentAsString(), "$.roverID");

        mvc.perform(put("/api/rover")
                        .content("{\"command\": \"LMLMLMLMM\", \"roverId\" : \"" + roverID_1 + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result_2 = mvc.perform(post("/api/rover")
                        .content("{\"command\": \"3 3 E\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("3 3 E"))
                .andReturn();
        String roverID_2 = JsonPath.read(result_2.getResponse().getContentAsString(), "$.roverID");

        mvc.perform(put("/api/rover")
                        .content("{\"command\": \"MMRMMRMRRM\", \"roverId\" : \"" + roverID_2 + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get("/api/rover/" + roverID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("1 3 N"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roverID").value(roverID_1));

        mvc.perform(get("/api/rover/" + roverID_2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("5 1 E"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roverID").value(roverID_2));
    }

    @Test
    public void testRoverNavigation_invalidCommandGrid() throws Exception {

        mvc.perform(post("/api/grid")
                        .content("{\"command\": \"ABC\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Invalid number of coordinates for grid size."));
    }

    @Test
    public void testRoverNavigation_invalidCommandRoverAdd() throws Exception {

        mvc.perform(post("/api/rover")
                        .content("{\"command\": \"ABC\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Invalid number of command values for rover positioning."));
    }

    @Test
    public void testRoverNavigation_invalidCommandRoverNavigate() throws Exception {

        mvc.perform(put("/api/rover")
                        .content("{\"command\": \"ABC\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Invalid values as control signals for rover navigation."));
    }

    @Test
    public void testRoverNavigation_getWithInvalidRoverID() throws Exception {

        mvc.perform(get("/api/rover/" + "ABC"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Rover with ID : ABC not found"));
    }

    @Test
    public void testRoverNavigation_navigateWithInvalidRoverID() throws Exception {

        mvc.perform(put("/api/rover")
                        .content("{\"command\": \"MMLRM\", \"roverId\" : \"ABC\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No existing rover with ID : ABC"));
    }

    @Test
    public void testRoverNavigation_invalidPositioning() throws Exception {

        mvc.perform(post("/api/grid")
                        .content("{\"command\": \"1 5\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        mvc.perform(post("/api/rover")
                        .content("{\"command\": \"2 2 N\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid position in the grid."));
    }

    @Test
    public void testRoverNavigation_invalidMovement() throws Exception {

        mvc.perform(post("/api/grid")
                        .content("{\"command\": \"3 4\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        MvcResult result = mvc.perform(post("/api/rover")
                        .content("{\"command\": \"2 2 N\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();
        String roverID = JsonPath.read(result.getResponse().getContentAsString(), "$.roverID");

        mvc.perform(put("/api/rover")
                        .content("{\"command\": \"MMLRM\", \"roverId\" : \"" + roverID + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid position with the movement in grid."));
    }

}
