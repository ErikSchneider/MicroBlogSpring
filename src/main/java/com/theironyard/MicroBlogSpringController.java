package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Erik on 6/20/16.
 */
@Controller
public class MicroBlogSpringController {

    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home (Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        if (username == null) {
            return "home";
        }
        else {
            Iterable<Message> msgs = messages.findAll();
            model.addAttribute("messages", msgs);

        }
        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String addMessage (String message) {
        Message msg = new Message(message);
        messages.save(msg);
        return "redirect:/";
    }
    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String edit(int id, String message) {
        Message msg = new Message(id, message);
        messages.save(msg);
        return "redirect:/";
    }
    @RequestMapping(path = "/delete-message", method = RequestMethod.POST)
    public String deleteMessage (Integer id) {
        messages.delete(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
