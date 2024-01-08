package com.sfmes.sy.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sfmes.cm.web.LoginVO;
import com.sfmes.cm.web.MyBuilderData;
import com.sfmes.sy.service.LoginService;

/**
 * @Class Name : LoginController.java
 * @Description : Egov Login Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.05.28   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.05.28
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class LoginController {
    /** EgovSampleService */
    @Resource(name = "LoginService")
    private LoginService loginService;

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;
    
    /**
     * 로그인 테스트
     * @param loginVO - 사용자 정보가 담긴 loginVO
     * @param model
     * @return "resultList"
     * @exception Exception
     */
    @RequestMapping(value = "/login.do")
    public String loginController(HttpServletRequest strData, HttpServletRequest request, ModelMap model) throws Exception {
        String strSVCID = null; //서비스 분기용 ID
        String returnMsg = null; //사용자메세지
        boolean isResult = false;
        
        // 파라미터 복호화를 수행한다.
        System.out.println("strData :: " + strData);
        myBuilderData.setParam(strData);
        System.out.println("myBuilderData :: " + myBuilderData);
        
        // 전송된 파라미터를 추출한다.
        strSVCID = myBuilderData.getParam("SVCID");  // 분기용 ID 
        //INMSV01 = myBuilderData.getParam("INMSV01"); 
        
        // 입력된 MSV타입 파라미터를 MAP형태로 변환한다.
        LinkedHashMap paramMap = myBuilderData.getParamFromMSVHashMap(myBuilderData.getParam("INMSV01"));
        //세션초기화
        request.getSession().removeAttribute("LOGIN_INFO");
        request.getSession().invalidate();
        
        System.out.println(paramMap + " ::::::::: [LoginController] paramMap ");
        
        //20.09.10 추가 여다혜
        String strS_CORP_C = (String)paramMap.get("S_CORP_C");
        System.out.println("S_CORP_C :: " + strS_CORP_C);
        
        //validKey가 null인 경우
        if(strS_CORP_C.equals("")) {
            returnMsg = "잘못된 접근입니다.\n회사코드 확인이 필요합니다.\n\n시스템운영자에게 문의하세요.";
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", returnMsg);
            
            return "responseToMybuilder";    
        }
        
        //20.09.10 추가 App.CORP_C (js에서 세팅한 Parameter)를 CORP_C로 덮어쓴다.
        paramMap.put("CORP_C", strS_CORP_C);
        
        // 0단계 : 회사코드(CORP_C)로 USE_YN 여부 확인 (사용가능한 회사인지 선검사 필요)
        String corpYn = loginService.selectCorpChk(paramMap);
        if(corpYn.equals("N")) {
            returnMsg = "사용중지된 회사입니다.\n\n시스템운영자에게 문의하세요.";
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", returnMsg);
            
            return "responseToMybuilder";
        }
        
        // 1단계 : USR_ID로 TB_SY_M_USR 테이블 조회, 사용자 존재 여부 확인 *없을 경우 returnMsg(사용자 메세지) 리턴
        List<?> resultList = loginService.selectUsrInfo(paramMap);
        if(resultList.size() <= 0) {
            returnMsg = "존재하지 않는 사용자입니다.";
            model.addAttribute("result", "OK");
            model.addAttribute("returnValue01", returnMsg);
            
            return "responseToMybuilder";
        } 

        // 2단계 : ID가 존재 할 경우 사용자정보 조회결과 List를 HashMap으로 변환
        LinkedHashMap usrInfoMap = (LinkedHashMap)resultList.get(0); //
        
        System.out.println(usrInfoMap + ":::: usrInfoMap");
        
        String inputPW = (String)paramMap.get("PW");    //화면에input된 PW
        String foundPW = (String)usrInfoMap.get("PW");  //Query조회결과 PW
        
        System.out.println(inputPW + " :::: inputPW");
        System.out.println(foundPW + " :::: foundPW");
        
        // 2-1단계 : PW비교 (사용자입력PW / DB에 저장된PW)
        if(inputPW.equals(foundPW)) {
            //2-2단계 : PW가 같을 경우
            String use_yn  = (String)usrInfoMap.get("USE_YN");  //사용여부 
            String lock_yn = (String)usrInfoMap.get("LOCK_YN"); //잠김여부(패스워드5회오류)
            String conn_yn = (String)usrInfoMap.get("CONN_YN"); //기존접속여부(세션)
            int basBzplCnt = loginService.selectBasBzplCnt(paramMap);
            System.out.println("basBzplCnt :::" + basBzplCnt);
            
            if(use_yn.equals("N")) {         //사용중지된 계정일 경우 
                returnMsg = "사용이 중지된 ID입니다.\n\n관리자에게 문의하세요.";
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", returnMsg);
            }else if(lock_yn.equals("Y")) {  //비밀번호 오류 5회 초과로 사용중지된 계정일 경우
                returnMsg = "비밀번호 오류 5회로 사용이 중지된 ID 입니다.\n\n관리자에게 문의하세요";
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", returnMsg);
            }else if(basBzplCnt <= 0) {
                //200831 추가 (여다혜)
                returnMsg = "대표사업장이 없습니다.\n\n시스템운영자에게 연락하세요.";
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", returnMsg);
            }
            
            //CONN_YN 으로 구분 안함
//            else if(conn_yn.equals("Y")) {  //기접속된 계정일 경우
//                
//                //***lgoin화면에서 서비스 재요청 할 때 분기
//                if(strSVCID.equals("CONN_YN_OK")) {
//                    isResult = true;
//                }else {
//                    returnMsg = "이미 접속중인 ID입니다.\n\n기존 접속을 해지하고 로그인 하시겠습니까?";
//                    model.addAttribute("result", "OK");
//                    model.addAttribute("returnValue02", returnMsg);
//                }
//            }
            
            else {
                isResult = true; //모든 조건이 맞을 경우(정상처리)
            }
        }else { // 2-2단계 : 비밀번호가 틀렸을 경우 
                //           1) 기존 DB의 비밀번호 오류 횟수 조회 (LGIN_PROV_NT 값)
                //           2) 오류횟수 5보다 작을 경우 => LGIN_PROV_NT 값 1회 증가
                //           3) 오류횟수 5일 경우        => LOCK_YN 값 'Y'로 변경 (계정잠김)
            
            int pwCnt = Integer.parseInt((String)usrInfoMap.get("LGIN_PROV_NT")); //PW오류 횟수 조회
            
            if(pwCnt < 5) {         // 2)오류횟수 5보다 작을 경우
                loginService.updateUsrLginProvNt(paramMap);      //로그인시도 횟수 1회 증가 update쿼리
                pwCnt = loginService.selectLginProvNt(paramMap); //1회 증가된 LGIN_PROV_NT 조회
                
                returnMsg = "비밀번호가 맞지 않습니다. 비밀번호를 다시 입력하기 바랍니다.\n\n"
                            + "로그인시도 횟수 (" + pwCnt + "/5)";
                if(pwCnt == 5) {
                	loginService.updateUsrLockYn(paramMap);     //LOCK_YN 여부를 'Y'로 update쿼리 (계정잠금)
                }
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", returnMsg);
            }else if(pwCnt == 5) {   // 3)오류횟수 5가 됐을 경우
                returnMsg = "비밀번호 오류 5회 누적으로 계정이 잠겼습니다.\n\n"
                            + "관리자에게 문의하시기 바랍니다.";
                model.addAttribute("result", "OK");
                model.addAttribute("returnValue01", returnMsg);
            }
        }       
        
        if( isResult == true ) {
            // 프레임워크 세션 설정을 한다.(추가할것)(사용자정보를 설정한다.)
            System.out.println("===== 사용자정상로그인(세션설정) ===== [" + usrInfoMap.toString() + "]");
            
            loginService.updateUsrConnInfo(paramMap); //로그인 사용자 최종접속 정보 업데이트 쿼리
            
            LoginVO loginVO = new LoginVO();
            loginVO.setCORP_C     ((String)usrInfoMap.get("CORP_C"));            /* 회사코드       */
            loginVO.setCORP_NM    ((String)usrInfoMap.get("CORP_NM"));           /* 회사명         */               
            loginVO.setUSR_ID     ((String)usrInfoMap.get("USR_ID"));            /* 사용자아이디   */
            loginVO.setUSR_NM     ((String)usrInfoMap.get("USR_NM"));            /* 사용자명       */
            loginVO.setBZPL_C     ((String)usrInfoMap.get("BZPL_C"));            /* 사업장코드     */
            loginVO.setBZPL_NM    ((String)usrInfoMap.get("BZPL_NM"));           /* 대표사업장명   */
            loginVO.setDEPT_C     ((String)usrInfoMap.get("DEPT_C"));            /* 부서코드       */
            loginVO.setPZC_C      ((String)usrInfoMap.get("PZC_C"));             /* 직급코드       */
            loginVO.setOFT_C      ((String)usrInfoMap.get("OFT_C"));             /* 직명코드       */
            loginVO.setUSE_DSC    ((String)usrInfoMap.get("USE_DSC"));           /* 사용자구분     */
            loginVO.setLS_CONN_IP ((String)usrInfoMap.get("LS_CONN_IP"));        /* 최종IP         */
            loginVO.setCONN_YN    ((String)usrInfoMap.get("CONN_YN"));           /* 접속여부       */
            loginVO.setACC_ST_DT  ((String)usrInfoMap.get("ACC_ST_DT"));         /* 계정시작일자   */
            loginVO.setACC_ED_DT  ((String)usrInfoMap.get("ACC_ED_DT"));         /* 계정종료일자   */
            loginVO.setADMIN_YN   ((String)usrInfoMap.get("ADMIN_YN"));          /* 관리자여부     */
            loginVO.setSVR_DATE   ((String)usrInfoMap.get("SVR_DATE"));          /* DB서버시간     */                                //서버시간 세팅
            loginVO.setEMP_ROL_DSC((String)usrInfoMap.get("EMP_ROL_DSC"));      /* 사용자역할구분  2022.04.18 나명우 추가 */
            request.getSession().setAttribute("LOGIN_INFO", loginVO);
            model.addAttribute("resultList",  resultList);
        } else {
            request.getSession().removeAttribute("LOGIN_INFO");
            request.getSession().invalidate();
        }

        return "responseToMybuilder";
    }
}
