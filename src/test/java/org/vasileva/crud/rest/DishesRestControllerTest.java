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
import org.vasileva.crud.ToJsonMapper;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.security.JwtProvider;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")

class DishesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private DishesRestController dishesRestController;

    @Test
    void testControllerIsNotNull() throws Exception {
        assertThat(dishesRestController).isNotNull();
    }

    @Test
    @Sql(value = {"/create-dishes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-dishes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getAllDishes() throws Exception {
        this.mockMvc.perform(get("/dishes/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Big Mac")))
                .andExpect(content().string(containsString("Hamburger")))
                .andExpect(content().string(containsString("Potato")));
    }

    @Test
    @Sql(value = {"/create-dishes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-dishes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getOneDishIsExists() throws Exception {
        this.mockMvc.perform(get("/dishes/2"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dishName").value("Hamburger"));
    }

    @Test
    void addDishIsCorrect() throws Exception {
        Dishes dish = new Dishes("zzz", 212.52, new BigDecimal("5.98"), 100);

        this.mockMvc.perform(post("/dishes/")
                .content(ToJsonMapper.toJson(dish))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Sql(value = {"/create-user-before.sql", "/create-dishes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-user-after.sql", "/create-dishes-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @WithUserDetails(value = "admin")
    void deleteDishIsCorrect() throws Exception {
        String token = jwtProvider.generateToken("admin");
        this.mockMvc.perform(delete("/dishes/", 1)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isNoContent());
    }

}