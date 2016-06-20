package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Erik on 6/20/16.
 */
@Controller
public class MicroBlogSpringController {

    ArrayList<Message> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = new User(username);
        }
        model.addAttribute("user", user);

        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage (String text) {
        messages.add(new Message(text));

    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
