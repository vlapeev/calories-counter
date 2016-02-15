package com.lapeevvd.controller.user;

import com.lapeevvd.dataTransferObject.UserTo;
import com.lapeevvd.model.User;
import com.lapeevvd.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/ajax/admin/users")
@RestController
public class AdminAjaxController extends AbstractUserController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    /* Use Binding */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createOrUpdate(@Valid UserTo userTo, BindingResult result){
        /*валидация с выводом ошибки если валидация валится*/
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fieldError -> sb.append(fieldError.getField())
                    .append(" ")
                    .append(fieldError.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (userTo.getId() == 0) {
            super.create(UserUtil.createFromUserTo(userTo));
        } else {
            super.update(userTo);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void enabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enabled(id, enabled);
    }
}
