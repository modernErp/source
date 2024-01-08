/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmmn.exception;

import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sfmes.cm.web.LoginVO;
import com.sfmes.sy.service.Sy4020Service;

/**
 * @Class Name : EgovBasicExcepHndlr.java
 * @Description : 프레임워크의 이벤트 발생 시 공통처리 이벤트헨들러
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.15  이철홍      최초생성
 *
 * @author 모든솔루션
 * @since 2020.06.15
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public class EgovBasicExcepHndlr implements ExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBasicExcepHndlr.class);
	
    @Resource(name = "Sy4020Service")
    private Sy4020Service callService;

	/**
	* @param ex
	* @param packageName
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	@Override
	public void occur(Exception ex, String packageName) {
		LOGGER.debug(" EgovSampleExcepHndlr packageName..............." + packageName);
		
        Object usrinfoObj = null;
        String strGUSRID_VAL = "admin";
        String strGCORP_VAL = "9999";
        String strLS_CONN_IP = "";
        
        // 오류 유형을 설정한다.
		StackTraceElement[] ste = ex.getStackTrace();
	    String className = ste[0].getClassName();
	    String methodName = ste[0].getMethodName();
	    int lineNumber = ste[0].getLineNumber();
	    String fileName = ste[0].getFileName();
		
        // 세션정보를 추출한다.
        usrinfoObj = RequestContextHolder.getRequestAttributes().getAttribute("LOGIN_INFO", RequestAttributes.SCOPE_SESSION);

        // 세션정보를 확인하고 조작자ID와 회사코드를 추출한다.
        if(usrinfoObj != null) {
            LoginVO loginVO = (LoginVO)usrinfoObj;

            /* 최초등록자, 최종변경자 설정하기 위하여 세션정보에서 아이디를 추출 */
            strGUSRID_VAL = loginVO.getUSR_ID();
            strGCORP_VAL = loginVO.getCORP_C();
            strLS_CONN_IP = loginVO.getLS_CONN_IP();
        }
        
        LinkedHashMap paramMap = new LinkedHashMap();
        
        // 시스템 오류 내역를 설정한다.
        paramMap.put("CORP_C", strGCORP_VAL);
        paramMap.put("GUSRID", strGUSRID_VAL);
        paramMap.put("LOG_DSC_NM", "logSave");
        paramMap.put("CONN_IP", strLS_CONN_IP);
        paramMap.put("PGM_ID", "");
        paramMap.put("CLASS_NM", className);
        paramMap.put("METHOD_NM", packageName);
        paramMap.put("SRC_FILE_NM", fileName);
        paramMap.put("LINE_NO", lineNumber);
        paramMap.put("ERR_MSG", ex.getMessage());
        
        // 시스템 오류 내역 테이블에 저장한다.
		callService.insertSy4020(paramMap);
	}
}
