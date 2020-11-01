package org.vasileva.crud.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/create-dishes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql", "/create-dishes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "admin")
class DishesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DishesRestController dishesRestController;

    @Test
    void testControllerIsNotNull() throws Exception {
        assertThat(dishesRestController).isNotNull();
    }

    @Test
    void getAllDishes() throws Exception {
        this.mockMvc.perform(get("/dishes/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Big Mac")))
                .andExpect(content().string(containsString("Hamburger")))
                .andExpect(content().string(containsString("Potato")));
    }

    @Test
    void getOneDish() throws Exception {
        this.mockMvc.perform(get("/dishes/1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dishName").value("Big Mac"));
    }

}