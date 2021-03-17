package whut_404notfound.audio_editing_tool.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import whut_404notfound.audio_editing_tool.domain.BaseResponse;
import whut_404notfound.audio_editing_tool.exception.IllegalRequestParamException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 全局异常处理类，可细分具体异常
 * 返回统一格式
 * 暂时用控制台输出日志，后续可通过依赖生成日志文件
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/3/14
 */
@ControllerAdvice
public class GlobalExceptionController {
    /**
     * 请求参数异常处理，状态码400
     */
    @ExceptionHandler(IllegalRequestParamException.class)
    @ResponseBody
    public BaseResponse IllegalRequestParamExceptionHandler(IllegalRequestParamException e){
        System.out.println(e);
        return new BaseResponse(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
    }

    /**
     * 404异常，暂时放这里，系统不会调用，可通过自定义“/error”实现返回所需body
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse noHandlerFound(NoHandlerFoundException e){
        System.out.println(e);
        return new BaseResponse(HttpServletResponse.SC_NOT_FOUND,Optional.ofNullable(e.getMessage()).orElse("请检查url是否正确"));
    }

    /**
     * 默认异常处理，状态码500
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse defaultExceptionHandler(Exception e){
        System.out.println(e);
        return new BaseResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Optional.ofNullable(e.getMessage()).orElse("服务器未知异常"));
    }
}
