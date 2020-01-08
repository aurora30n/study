package study.springbootsession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String pwd) {

        if (pwd.equals(username)) {
            request.getSession().setAttribute("username", username);
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request) {
        return request.getSession().getAttribute("username")==null?"not login":request.getSession().getAttribute("username").toString();
    }

}
