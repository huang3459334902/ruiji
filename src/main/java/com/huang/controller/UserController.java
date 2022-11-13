package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huang.common.R;
import com.huang.entity.User;
import com.huang.service.UserService;
import com.huang.utils.MailUtils;
import com.huang.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * 用户信息(User)表控制层
 *
 * @author makejava
 * @since 2022-11-10 16:37:21
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @Autowired
    private MailUtils mailUtils;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {

        String mail = user.getMail();
        log.info("mail:"+mail);

        if (Objects.nonNull(mail)) {
//            mailUtils.sendMail(mail,session);
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code:"+code);
            session.setAttribute("code",code);
        }
        return R.success("验证码发送成功");
    }


    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        Object code = session.getAttribute("code");

        if (Objects.nonNull(code) && code.equals(map.get("code"))) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(Objects.nonNull(map.get("mail")),User::getMail,map.get("mail"));
            User user = userService.getOne(userLambdaQueryWrapper);
            if (Objects.isNull(user)) {
                user = new User();
                user.setMail((String) map.get("mail"));
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }

        return R.error("登录失败");
    }

    @PostMapping("/loginout")
    public R logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }


}

