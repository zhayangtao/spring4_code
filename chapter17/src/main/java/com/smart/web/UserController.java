package com.smart.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

/**
 * Created by shliangyan on 2017/10/26.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping("register")
    public String register() {
        return "user/register";
    }

    @RequestMapping("/{userId}")
    public ModelAndView showDetail(@PathVariable("useriD") String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/showDetail");
        modelAndView.addObject("user", userId);
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "userId")
    public String test1(@RequestParam("userId") String userId) {
        return "user/test1";
    }

    @RequestMapping("/handle1")
    public String handle1(@RequestParam("userName") String userName, @RequestParam("password") String password,
                          @RequestParam("realName") String realName) {
        return "success";
    }

    @RequestMapping(value = "/handle2")
    public ModelAndView handle2(@CookieValue("JSESSIONID") String sessionId, @RequestHeader("Accept-Language") String acceptLanguage) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("s");
        modelAndView.addObject("user", null);
        return modelAndView;
    }

    public void findBookId(@MatrixVariable int a) {

    }

    @RequestMapping(path = "/handle21")
    public void handle21(HttpServletRequest request, HttpServletResponse response) {
        String userName = WebUtils.findParameterValue(request, "username");
        response.addCookie(new Cookie("userName", userName));
    }

    @RequestMapping(path = "/handle31")
    public void handle31(OutputStream os) throws IOException {
        Resource resource = new ClassPathResource("/image.jpg");
        FileCopyUtils.copy(resource.getInputStream(), os);
    }

    @ResponseBody
    @RequestMapping(path = "/handle42/{imageId}")
    public byte[] handle42(@PathVariable("imageId") String imageId) throws IOException {
        System.out.println("load iamge of " + imageId);
        Resource resource = new ClassPathResource("/image.jpg");
        byte[] fileDate = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return fileDate;
    }

    public ResponseEntity<String> handle51(HttpEntity<String> requestEntity) {
        requestEntity.getBody();
        return new ResponseEntity<String>("1", HttpStatus.OK);
    }


}
