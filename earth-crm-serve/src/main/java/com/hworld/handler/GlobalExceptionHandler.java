package com.hworld.handler;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.hworld.base.BaseResponse;
import com.hworld.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局捕获异常
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler
    public BaseResponse<String> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        // 打印堆栈，以供调试
        log.error("全局捕获异常：{}", e);
        if (e instanceof NotLoginException) {    // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            return BaseResponse.error(ErrorEnum.PERMISSION_ERROR.getCode(), "User is not logged in:");
        } else if (e instanceof NotRoleException) {        // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            return BaseResponse.error(ErrorEnum.PERMISSION_ERROR.getCode(), "No such role:" + ee.getRole());
        } else if (e instanceof NotPermissionException) {    // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            return BaseResponse.error(ErrorEnum.PERMISSION_ERROR.getCode(), "No such permission:" + ee.getCode());
        } else if (e instanceof DisableLoginException) {    // 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            return BaseResponse.error(ErrorEnum.PERMISSION_ERROR.getCode(), "Account is blocked:" + ee.getDisableTime() + "秒后解封");
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ex.getBindingResult();

            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String field = error.getField();
                Object value = error.getRejectedValue();
                String msg = error.getDefaultMessage();
                String message = String.format("Error field:%s,Error value：%s,reason：%s;", field, value, msg);
                stringBuilder.append(message).append("\r\n");
            }
            return BaseResponse.error(stringBuilder.toString());
        } else {    // 普通异常, 输出：500 + 异常信息
            return BaseResponse.error(ErrorEnum.SYSTEM_ERROR.getCode(), "System error!");
        }
    }
}
