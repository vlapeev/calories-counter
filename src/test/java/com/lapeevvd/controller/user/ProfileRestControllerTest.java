package com.lapeevvd.controller.user;

import com.lapeevvd.TestUtil;
import com.lapeevvd.controller.AbstractControllerTest;
import com.lapeevvd.controller.json.JsonUtil;
import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import com.lapeevvd.util.LoggedUser;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Collections;

import static com.lapeevvd.TestUser.MATCHER;
import static com.lapeevvd.UserTestData.ADMIN;
import static com.lapeevvd.UserTestData.USER;
import static com.lapeevvd.controller.user.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
    TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(LoggedUser.getId(), "newName", "newPassword", "newEmail", Role.USER);
        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, new User(userService.getByEmail("newEmail")));
    }
}