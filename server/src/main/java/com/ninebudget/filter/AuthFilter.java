package com.ninebudget.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.Token;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.model.dto.CredentialDto;
import com.ninebudget.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Optional;

public class AuthFilter extends OncePerRequestFilter {
    protected static final Logger log = LogManager.getLogger(AuthFilter.class);

    @Autowired
    private Token<String> jwtToken;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*
            Check to make sure it's only coming in for the API
            Don't want to check for the UI or Auth
         */
        if(!request.getPathInfo().contains("/api")
                || request.getPathInfo().contains("/oauth/token")
                || request.getPathInfo().contains("/password/reset")
                || request.getPathInfo().contains("/activate")){
            //Continue on the path the request needed
            chain.doFilter(request, response);

            return;
        }

        String requestTokenHeader = request.getHeader("Authorization");

        //Check to make sure we have Authorization
        if(requestTokenHeader == null){
            log.error("No Authentication Provided");

            //Set error and Response
            ServiceException exception = new ServiceException(HttpStatus.UNAUTHORIZED, "FATAL", "No Authentication Provided");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            //Print out error Object
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(exception));
            out.flush();

            return;
        }

        try{
            //Token is in the form of "Bearer {token}". Remove Bearer work and get only the token
            String authString = requestTokenHeader.substring(7);

            jwtToken.setToken(authString);

            //Authenticate Token
            jwtToken.authenticate();

            //Place in Spring for others to use, if empty
            if(SecurityContextHolder.getContext().getAuthentication() == null){
                //Grab Account ID from DB
                CredentialDto cred = new CredentialDto();
                cred.setUsername(jwtToken.getSubject());
                Optional<ApplicationUserDto> dbUser = userService.findOneByCredential(cred);

                //Error if we cannot find the User in the DB
                if(!dbUser.isPresent()){
                    throw new Exception();
                }

                User principal = new User(dbUser.get().getAccount().getId().toString(), "", Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, jwtToken, Collections.emptyList()));
            }
        }catch(Exception e){
            log.error("Unable to Validate Token " + e);

            //Set error and Response
            ServiceException exception = new ServiceException(HttpStatus.UNAUTHORIZED, "FATAL", "Could not verify signature of Token");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            //Print out error Object
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(exception));
            out.flush();

            return;
        }

        log.info("Token has been verified");

        //Continue on the path the request needed
        chain.doFilter(request, response);
    }
}
