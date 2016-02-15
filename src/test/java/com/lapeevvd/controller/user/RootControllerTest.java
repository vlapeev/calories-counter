package com.lapeevvd.controller.user;

import com.lapeevvd.controller.AbstractControllerTest;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                /*.andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"))*/
                .andExpect(redirectedUrl("http://localhost/login"));
                /*.andExpect(model().attribute("userList", Matchers.hasSize(2)))
                .andExpect(model().attribute("userList", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("id", Matchers.is(AbstractEntity.START_SEQ)),
                                Matchers.hasProperty("name", Matchers.is(UserTestData.ROLE_USER.getName()))
                        )
                )));*/
    }
}