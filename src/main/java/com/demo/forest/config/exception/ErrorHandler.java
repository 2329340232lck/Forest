package com.demo.forest.config.exception;

import com.demo.forest.util.ResponseInfo;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ErrorHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private ErrorAttributes errorAttributes;

    private ErrorHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    //处理页面404错误
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(Model model, HttpServletRequest request, HttpServletResponse response) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, false);
        if (!errorAttributes.isEmpty()) {
            System.err.println("错误参数打印:");
            for (String key : errorAttributes.keySet()) {
                System.err.println(key + " : " + errorAttributes.get(key));
            }
        }
        model.addAttribute("errorMessage", "ERROR 404,所寻找的页面不存在!");
        return "/error";
    }

    @ResponseBody
    @RequestMapping(value = ERROR_PATH)
    public ResponseInfo errorAjaxHandler(HttpServletRequest request, HttpServletResponse response) {
        return ResponseInfo.ERROR(404, "错误,所寻找的资源不存在!");
    }

}
