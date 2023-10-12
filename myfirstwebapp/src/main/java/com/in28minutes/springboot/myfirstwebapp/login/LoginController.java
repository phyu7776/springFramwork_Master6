package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private AutheticationService autheticationService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login () {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String gotoWelcomePage (@RequestParam String name, @RequestParam String password, ModelMap model) {

        if (autheticationService.authenticate(name, password)) {
            model.put("name",name);
            model.put("password",password);

            return "welcome";
        }
        model.put("isWrong", "Invaild Login");
        return "login";
    }
}
