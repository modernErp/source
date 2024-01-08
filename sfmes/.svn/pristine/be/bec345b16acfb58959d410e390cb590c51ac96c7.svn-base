package com.sfmes.sy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfmes.cm.web.LoginVO;
import com.sfmes.sy.service.LogoutService;

/**
 * @Class Name : LogoutController.java
 * @Description : LogoutController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.08.21   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.08.21
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class LogoutController {
    /** EgovSampleService */
    @Resource(name = "LogoutService")
    private LogoutService logoutService;

    
    @RequestMapping(value = "/logout.do")
    public String loginController(HttpServletRequest request, ModelMap model) throws Exception {
        String returnMsg = null; //사용자메세지
                
        HttpSession session = request.getSession();
        LoginVO loginVO = null;
        
        if(session.getAttribute("LOGIN_INFO") != null) {
            loginVO = (LoginVO)session.getAttribute("LOGIN_INFO");
            //logoutService.updateUsrDisconnInfo(loginVO);
            
            //세션종료
            request.getSession().removeAttribute("LOGIN_INFO");
            request.getSession().invalidate(); 
            
            System.out.println("LOGOUT / EOS");
        }else {
            System.out.println("loginVO 가 NULL 입니다. 종료 할 SESSION이 없음.");

        
        }

        return "responseToMybuilder";
    }
}
