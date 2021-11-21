package org.example.job.exception;


import org.example.job.pojo.exception.BusinessException;

import org.example.job.pojo.exception.SystemException;
import org.example.job.pojo.model.enums.ResultCode;
import org.example.job.pojo.model.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 85217
 * @Modify 2021-11-16 23:12
 * @Description <p> </p>
 * <p> Talk is cheap . Show me the code! </p>
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 处理系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = SystemException.class)
    public Result handleSystemException(SystemException e) {
        // 记录日志
        log.error(e.getResultCode().getValue() + ":{}", e.getMessage());
        // 发送邮件

        ResultCode resultCode = e.getResultCode();
        // 提示用户
        return Result.fail(resultCode);
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        ResultCode resultCode = e.getResultCode();
        return Result.fail(resultCode);
    }

    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleUnKnowException(Exception e) {

        return null;
    }

}
