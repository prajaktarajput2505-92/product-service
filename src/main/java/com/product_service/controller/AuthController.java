package com.product_service.controller;

import com.product_service.utility.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private JwtUtil jwtUtil;

    @RequestMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password)
    {
        if("admin".equals(userName) && "password".equals(password))
        {
          return jwtUtil.generateToken(userName);
        }
        else
        {
            throw new RuntimeException("Invalid Credentials");
        }

    }
}
