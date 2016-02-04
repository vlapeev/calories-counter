package com.lapeevvd.controller.user;

import com.lapeevvd.model.Role;
import com.lapeevvd.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/ajax/admin/users")
@RestController
public class AdminAjaxController extends AbstractUserController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return super.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@PathVariable("id") int id,
                               @PathVariable("name") String name,
                               @PathVariable("password") String password,
                               @PathVariable("email") String email){
        User user = new User(id, name, password, email, Role.USER);
        if (id == 0){
            super.create(user);
        } else {
            super.update(user, id);
        }
    }
}
