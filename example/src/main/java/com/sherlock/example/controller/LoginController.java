package com.sherlock.example.controller;

import com.sherlock.example.core.rest.ResultEntity;
import com.sherlock.model.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 16:13
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public ResultEntity<?> login(@RequestBody UserVo userVo, @RequestParam(value = "captcha", required = false) String captcha) {
        Subject subject = SecurityUtils.getSubject();

        // 如果开启了登录校验
        String realCaptcha = (String) SecurityUtils.getSubject().getSession().getAttribute("captcha");
        if (realCaptcha == null || !realCaptcha.equals(captcha.toLowerCase())) {
            throw new NullPointerException("验证码不能为空");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUserName(), userVo.getPassword());
        subject.login(token);
        return ResultEntity.success("登录成功");
    }
}
