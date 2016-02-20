package com.lapeevvd.controller.user;

import com.lapeevvd.TestUtil;
import com.lapeevvd.controller.AbstractControllerTest;
import com.lapeevvd.controller.json.JsonUtil;
import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.User;
import com.lapeevvd.util.UserUtil;
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
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(TestUtil.userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER)));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON).with(TestUtil.userHttpBasic(USER)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), userService.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        UserTo updatedTo = new UserTo(0, "newName", "newEmail", "newPassword", 1500);

        mockMvc.perform(put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(TestUtil.userHttpBasic(USER))
                .content(JsonUtil.writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(UserUtil.updateFromUserTo(new User(USER), updatedTo), userService.getByEmail("newemail"));
    }
}