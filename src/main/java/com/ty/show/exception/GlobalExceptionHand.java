package com.ty.show.exception;

import com.ty.show.dataobject.ResData;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHand {


    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("error", "error");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResData errorHandler(BaseException ex) {
        ResData webResultResponse = new ResData();
        webResultResponse.setMsg(ex.getMessage());
        webResultResponse.setCode(ex.getCode());
        return webResultResponse;
    }
}
