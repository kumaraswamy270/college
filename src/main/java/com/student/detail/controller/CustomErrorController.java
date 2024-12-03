package com.student.detail.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            model.addAttribute("status", statusCode);

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("message", "Page not found");
            } else {
                model.addAttribute("message", "Internal server error");
            }
        }

        return "error";  // Ensure this corresponds to an actual view (error.jsp or error.html)
    }

    public String getErrorPath() {
        return "/error";  // Ensure this points to a valid endpoint
    }
}
