package org.yagna.samples.angularspringmongo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yagna.samples.angularspringmongo.auth.UserAuthentication;
import org.yagna.samples.angularspringmongo.model.AuthStatus;
import org.yagna.samples.angularspringmongo.model.UserLogin;
import org.yagna.samples.angularspringmongo.service.TokenAuthenticationService;
import org.yagna.samples.angularspringmongo.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asish on 4/2/16.
 */
@RestController
public class AuthenticationController {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/api/login", method= RequestMethod.POST)
    public AuthStatus login(HttpServletResponse response, @RequestBody UserLogin userLogin) throws IOException {
        LOG.info("User trying to Login - {}", userLogin);
        List<GrantedAuthority> roles =  new ArrayList<GrantedAuthority>();
        User user = new User(userLogin.getUserName(), userLogin.getPassword(), roles);
        UserAuthentication userAuthentication = new UserAuthentication(user);
        this.tokenAuthenticationService.addAuthentication(response, userAuthentication);
        userService.addUser(user);
        return new AuthStatus("true");
    }

    @RequestMapping(value="/api/logout", method= RequestMethod.GET)
    public AuthStatus logout() throws IOException {
        LOG.info("User trying to LogOut");
        return new AuthStatus("true");
    }
}
