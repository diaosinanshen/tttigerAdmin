package com.tttiger.admin.controller.sys;

import com.tttiger.admin.bean.sys.Menu;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.CommonValid;
import com.tttiger.admin.service.sys.MenuService;
import com.tttiger.admin.utils.SecurityUtil;
import com.tttiger.admin.utils.VerifyCodeUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:56
 * @Description
 */
@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

    private MenuService menuService;


    @GetMapping("/captcha")
    public void getLoginCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String s = VerifyCodeUtils.outputVerifyImage(190, 80, response.getOutputStream(), 4);
        session.setAttribute("captcha",s);
    }

    @GetMapping("/pub-key")
    @ResponseBody
    public ResultMap<String> getPubKey() throws Exception {
        return ResultMap.data(SecurityUtil.setServerRsa()).success();
    }

    @CommonValid
    @PostMapping("/security-confirm")
    @ResponseBody
    public ResultMap<String> setClientKey(@NotBlank(message = "非法参数") String clientKey) throws Exception {
        return ResultMap.data(SecurityUtil.setTransportAes(clientKey)).success();
    }

    @GetMapping("/current-manager")
    @ResponseBody
    public ResultMap<String> selectCurrentManager(){
        Authentication currentUser = SecurityUtil.getCurrentUser();
        return ResultMap.data(currentUser.getName()).success();
    }


    @GetMapping("/select-authority-menu")
    @ResponseBody
    public ResultMap<List<Menu>> selectAuthorityMenu(){
        Authentication currentUser = SecurityUtil.getCurrentUser();
        String userAccount = currentUser.getName();
        List<Menu> menus = menuService.selectUserHasAuthorityMenu(userAccount);
        return ResultMap.data(menus).success();
    }


}
