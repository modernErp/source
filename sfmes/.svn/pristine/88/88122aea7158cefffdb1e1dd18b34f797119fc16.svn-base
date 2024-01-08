
package com.sfmes.cm.interceptor.service;

import java.util.List;
import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sfmes.cm.service.UserUseLogService;
import com.sfmes.cm.web.LoginVO;
import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.cm.web.MyBuilderData_PDA;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 이철홍
 * @since 2020.06.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2020.06.16  이철홍     최초작성
 *  2021.03.17  장경석     PDA용 추가 (MyBuilderData_PDA)
 *  </pre>
 */

public class SessionInterceptor implements HandlerInterceptor {
	
    @Resource(name = "UserUseLogService")
    private UserUseLogService callService;
	
    List<String> urls;

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    /**
     * 세션만료시 eos.jsp페이지로 이동한다.
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
    {
    	
        try {
            System.out.println("requestURL :: " + request.getRequestURL().toString());
            
            HttpSession session = request.getSession();
            String requestUrl = request.getRequestURL().toString();
            
            // login 서비스 호출 시 세션체크 하지 않는다
            if( requestUrl.indexOf("login.do") > 0 ) {
            	return true;
            }
            
            // 세션정보를 추출한다.
            LoginVO loginVO = (LoginVO)session.getAttribute("LOGIN_INFO");

            // 세션정보가 있는 경우 정상 리턴한다.
            if(loginVO != null) {
            	
                /* 최초등록자, 최종변경자 설정하기 위하여 세션정보에서 아이디를 추출 */
                this.UserUseLogInsert(request, loginVO);
                return true;
            }            
            
            // 세션정보가 없는 경우 세션종료 처리한다.
            //response.sendRedirect("eos.jsp");
            return true;

        } catch (Exception e) {
            System.out.println("SessionInterceptor preHandle error =========" + e.getMessage());
        }
        
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
    
    // 시스템 사용 이력을 저장한다.
    private void UserUseLogInsert(HttpServletRequest request, LoginVO inloginVO) throws Exception {
        MyBuilderData     myBuilderData     = new MyBuilderData();
        MyBuilderData_PDA myBuilderData_PDA = new MyBuilderData_PDA();
        
    	LinkedHashMap<String, String> logMap = new LinkedHashMap<String, String>();						// 사용이력 로그맵
        try {
	    	String prgId = request.getRequestURL().toString();
	    	
	    	// 2021.03.17 JKS 
	    	// movile/on-line 용 구분 처리 추가
	    	// movile 용과  on-line 용을 구분해서 사용
	    	if(prgId.indexOf("PDA") < 0) {
	    	    prgId = prgId.substring(prgId.indexOf("sfmes")+6);
	    	    
	    	    System.out.println("prgId :: " + prgId);
	    	    myBuilderData.setParam(request.getParameter("data"));  //파라미터 복호화

	            logMap.put("CORP_C",    inloginVO.getCORP_C());                 /* 프로그램ID           */
	            logMap.put("PGM_ID",    prgId);                                 /* CLASS명              */
	            logMap.put("USR_ID",    inloginVO.getUSR_ID());                 /* METHOD명             */
	            logMap.put("CONN_IP",   inloginVO.getLS_CONN_IP());             /* 사용자아이디         */
	            logMap.put("CLASS_NM",  prgId);                                 /* 접속IP               */
	            logMap.put("PARA_DSC_NM",  "SVCID");                            /* 파라미터구분명       */
	            logMap.put("PARA_DSC_VAL",  myBuilderData.getParam("SVCID"));   /* 파라미터구분값       */
	            logMap.put("PARA1_NM",  "INMSV01");                             /* 파라미터명1          */
	            logMap.put("PARA1_VAL",  myBuilderData.getParam("INMSV01"));    /* 파라미터값1          */
	            logMap.put("PARA2_NM",  "INMSV02");                             /* 파라미터명2          */
	            logMap.put("PARA2_VAL",  myBuilderData.getParam("INMSV02"));    /* 파라미터값2          */
	            logMap.put("PARA3_NM",  "INMSV03");                             /* 파라미터명3          */
	            logMap.put("PARA3_VAL",  myBuilderData.getParam("INMSV03"));    /* 파라미터값3          */
	            logMap.put("PARA4_NM",  "INMSV04");                             /* 파라미터명4          */
	            logMap.put("PARA4_VAL",  myBuilderData.getParam("INMSV04"));    /* 파라미터값4          */
	            logMap.put("PARA5_NM",  "INMSV05");                             /* 파라미터명5          */
	            logMap.put("PARA5_VAL",  myBuilderData.getParam("INMSV05"));    /* 파라미터값5          */
	            logMap.put("PARA6_NM",  "INMSV06");                             /* 파라미터명6          */
	            logMap.put("PARA6_VAL",  myBuilderData.getParam("INMSV06"));    /* 파라미터값6          */
	            
	    	} else {
	    	    prgId = prgId.substring(prgId.indexOf("sfmes")+6);
	    	    
	    	    System.out.println("PDA prgId :: " + prgId);
	            // 순차적을 보낸다.
	    	    myBuilderData_PDA.setParam("cid",        request.getParameter("cid"));
	            myBuilderData_PDA.setParam("uid",        request.getParameter("uid"));
	            myBuilderData_PDA.setParam("cmd",        request.getParameter("cmd"));
	            myBuilderData_PDA.setParam("pos",        request.getParameter("pos"));
	            myBuilderData_PDA.setParam("mod",        request.getParameter("mod"));
	            myBuilderData_PDA.setParam("paramValue", request.getParameter("paramValue"));
	            myBuilderData_PDA.setParam("end",        request.getParameter("end"));
	            myBuilderData_PDA.setParam("run",        request.getParameter("run"));
	            myBuilderData_PDA.setParam("SVCID",      request.getParameter("SVCID"));
	            myBuilderData_PDA.setParam("INMSV01",    request.getParameter("INMSV01"));
	    	    //myBuilderData_PDA.setParam(request.getParameter("INMSV01"));  //파라미터 복호화 없이 decode 만 사용

	            logMap.put("CORP_C",       inloginVO.getCORP_C());                     /* 프로그램ID          */
	            logMap.put("PGM_ID",       prgId);                                     /* CLASS명                   */
	            logMap.put("USR_ID",       inloginVO.getUSR_ID());                     /* METHOD명                  */
	            logMap.put("CONN_IP",      inloginVO.getLS_CONN_IP());                 /* 사용자아이디                */
	            logMap.put("CLASS_NM",     prgId);                                     /* 접속IP             */
	            logMap.put("PARA_DSC_NM",  "SVCID");                                   /* 파라미터구분명              */
	            logMap.put("PARA_DSC_VAL", myBuilderData_PDA.getParam("SVCID"));       /* 파라미터구분값              */
	            logMap.put("PARA1_NM",     "INMSV01");                                 /* 파라미터명1          */
	            logMap.put("PARA1_VAL",    myBuilderData_PDA.getParam("INMSV01"));     /* 파라미터값1          */
	            logMap.put("PARA2_NM",     "INMSV02");                                 /* 파라미터명2          */
	            logMap.put("PARA2_VAL",    myBuilderData_PDA.getParam("INMSV02"));     /* 파라미터값2          */
	            logMap.put("PARA3_NM",     "INMSV03");                                 /* 파라미터명3          */
	            logMap.put("PARA3_VAL",    myBuilderData_PDA.getParam("INMSV03"));     /* 파라미터값3          */
	            logMap.put("PARA4_NM",     "INMSV04");                                 /* 파라미터명4          */
	            logMap.put("PARA4_VAL",    myBuilderData_PDA.getParam("INMSV04"));     /* 파라미터값4          */
	            logMap.put("PARA5_NM",     "INMSV05");                                 /* 파라미터명5          */
	            logMap.put("PARA5_VAL",    myBuilderData_PDA.getParam("INMSV05"));     /* 파라미터값5          */
	            logMap.put("PARA6_NM",     "INMSV06");                                 /* 파라미터명6          */
	            logMap.put("PARA6_VAL",    myBuilderData_PDA.getParam("INMSV06"));     /* 파라미터값6          */
	            
	    	}
	        
        	// 사용자 사용 이력 로그를 저장한다.
        	callService.insertUseLog(logMap);
	    } catch (Exception e) {
	    	// 사용자 사용 이력 로그 저장 중 오류 발생시 PASS한다.(추후 오류 분석하여 조치하는 구조)
	        System.out.println("SessionInterceptor 사용자 사용 이력 저장 중 오류 발생 : " + e.getMessage());
	    }
    }
}
