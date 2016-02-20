package com.lapeevvd.controller.user;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.User;
import com.lapeevvd.util.LoggedUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/profile")
public class ProfileRestController extends AbstractUserController {
    public static final String REST_URL = "/rest/profile";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(LoggedUser.getId());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(LoggedUser.getId());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserTo userTo) {
        userTo.setId(LoggedUser.getId());
        super.update(userTo);
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String testUTF() {
        return "Русский текст";
    }
}
