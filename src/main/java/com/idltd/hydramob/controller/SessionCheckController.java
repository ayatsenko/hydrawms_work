package com.idltd.hydramob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/sessioncheck")
public class SessionCheckController {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    private void closesession(){
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); // true == allow create
        session.invalidate();
    }

    @RequestMapping(value = {"","/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(){
        String result="Active";
        try(Connection conn = dataSource.getConnection()) {
            if (!conn.isValid(30)) {
                closesession();
                result="Fail";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            closesession();
            result="Fail";
        }

        return result;
    }

}
