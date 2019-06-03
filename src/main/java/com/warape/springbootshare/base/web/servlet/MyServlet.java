package com.warape.springbootshare.base.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: springboot-share
 * @description: 异步servlet3.0
 * @author: 万明宇
 * @create: 2019-05-28 14:25
 **/
@WebServlet(urlPatterns = "/myServlet", asyncSupported = true)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                resp.getWriter().println("hello-world");
                //触发
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
