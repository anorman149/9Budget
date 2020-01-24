package com.ninebudget.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthFilter extends OncePerRequestFilter {
    protected static final Logger log = LogManager.getLogger(AuthFilter.class);

    @Autowired
    private Token<String> jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*
            Check to make sure it's only coming in for the API
            Don't want to check for the UI or Auth
         */
        if(!request.getPathInfo().contains("/api") || request.getPathInfo().contains("/oauth/token")){
            //Continue on the path the request needed
            chain.doFilter(request, response);

            return;
        }

        String requestTokenHeader = request.getHeader("Authorization");

        //Check to make sure we have Authorization
        if(requestTokenHeader == null){
            log.error("No Authentication Provided");

            //Set error and Response
            ServiceException exception = new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "No Authentication Provided");
            response.setStatus(HttpStatus.FORBIDDEN.value());
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
        }catch(Exception e){
            log.error("Unable to Validate Token " + e);

            //Set error and Response
            ServiceException exception = new ServiceException(HttpStatus.FORBIDDEN.toString(), "FATAL", "Could not verify signature of Token");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            //Print out error Object
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(exception));
            out.flush();

            return;
        }

        log.info("Token has been verified");

        //Place in HTTP Session for APIs to use
        request.getSession().setAttribute("token", jwtToken);

        //Continue on the path the request needed
        chain.doFilter(request, response);
    }
}
