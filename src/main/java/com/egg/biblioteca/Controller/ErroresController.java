package com.egg.biblioteca.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresController implements ErrorController {
    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView renderErrorPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        String errorMsg = "";
        Integer errorCode = getErrorCode(request);
        switch (errorCode) {
            case 400:
                errorMsg = "Error 400";
                break;
            case 401:
                errorMsg = "Error 401";
                break;
            case 404:
                errorMsg = "Error 404";
                break;
            case 500:
                errorMsg = "Error 500";
                break;
        }
        mav.addObject("errorCode", errorCode);
        mav.addObject("errorMsg", errorMsg);
        mav.setViewName("error");
        return mav;
    }

    private int getErrorCode(HttpServletRequest request) {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        return statusCode instanceof Integer ? (Integer) statusCode : 500;
    }
}
