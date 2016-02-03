package com.lapeevvd.controller.user;

import com.lapeevvd.UserTestData;
import com.lapeevvd.controller.AbstractControllerTest;
import com.lapeevvd.model.AbstractEntity;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends AbstractControllerTest{

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"))
                .andExpect(model().attribute("userList", Matchers.hasSize(2)))
                .andExpect(model().attribute("userList", Matchers.hasItem(
                        Matchers.allOf(
                                Matchers.hasProperty("id", Matchers.is(AbstractEntity.START_SEQ)),
                                Matchers.hasProperty("name", Matchers.is(UserTestData.USER.getName()))
                        )
                )));
    }
}