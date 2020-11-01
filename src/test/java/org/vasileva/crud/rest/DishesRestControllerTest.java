package org.vasileva.crud.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DishesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DishesRestController dishesRestController;

    @Test
    void testControllerIsNotNull ()  throws Exception {
        assertThat(dishesRestController).isNotNull();
    }

    @Test
    void getStaff() throws Exception {
        this.mockMvc.perform(get("/dishes/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

}