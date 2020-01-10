package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {

        model.addAttribute(new User());
        return "user/add";
    }

//    @PostMapping
//    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
//        model.addAttribute("user", user);
//        model.addAttribute("verify", verify);
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("email", user.getEmail());
//        if (user.getPassword().equals(verify)) {
//           return "user/index";
//        }
//        else {
//            model.addAttribute("error", "Passwords do not match");
//            return "user/add";
//        }
//
//    }

    @PostMapping
    public String processAddUserForm(@ModelAttribute @Valid User user,
                                     Errors errors, Model model) {

        if(!user.getPassword().equals(user.getVerify())){
            model.addAttribute("error", "Passwords do not match.");
            return "user/add";

        }

        else if(errors.hasErrors()){
            model.addAttribute("error", "There are errors in this form.");;
            return "user/add";
        }

        else {
            return "user/index";

        }

    }




}
