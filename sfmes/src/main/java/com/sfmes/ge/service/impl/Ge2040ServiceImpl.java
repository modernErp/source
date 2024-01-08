package com.sfmes.ge.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popbill.api.KakaoService;
import com.popbill.api.PopbillException;
import com.popbill.api.fax.FaxServiceImp;
import com.popbill.api.fax.Receiver;
import com.popbill.api.kakao.ATSTemplate;
import com.popbill.api.kakao.KakaoButton;
import com.popbill.api.kakao.KakaoReceiver;
import com.popbill.api.kakao.KakaoServiceImp;
import com.popbill.api.message.Message;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.ge.service.Ge2040Service;
import com.popbill.api.message.MessageServiceImp;

/**
 * @Class Name : Ge2040ServiceImpl.java
 * @Description : 외부연락연동
 * @Modification Information
 * @
 * @  수정일     수정자      수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.02.04  박지환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2021.02.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

@Service("Ge2040Service")
public class Ge2040ServiceImpl extends CmnAbstractServiceImpl implements Ge2040Service {
    
    // 팝빌에 등록된 회원정보
    private String linkID = "MODERNSOLU";
    private String secretKey = "YtbAbisTFmPrlRTO40lTwYb4kJdaFVglQjKKb5CMhkU=";
    private boolean test = true;
    private boolean IPRestrictOnOff = true;
    private boolean useStaticIP = false;
    private boolean useLocalTimeYN = true;
    // 팝빌회원 사업자번호
    private String corpNum = "2648149853";
    // 팝빌회원 아이디
    private String userID = "MODERNSOLU";
    // 팝빌회원 발신등록번호
    private String sendNum = "0313607870";
    
    @Autowired
    private SqlSessionTemplate sqlSession;
    
    
    //수신자 목록 리스트 조회
    @Override
    public List<?> selectGe2040_01(LinkedHashMap<String, Object> paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.ge.selectGe2040_01", paramMap);
    }
    
    /**
	 * 문자전송
	 * @param1 paramMap - 문자전송 정보
	 * @param2 paramList - 문자 수신자 리스트
	 * @return 전송 성공 여부
	 * @exception Exception
	 */
    @Override
    public String msgSend(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************* SMS 연동 [msgSend] ****************");
        
        // 팝빌 문자api 객체생성
        MessageServiceImp messageService = new MessageServiceImp();
        
        // 필수정보 세팅
        messageService.setLinkID(linkID);
        messageService.setSecretKey(secretKey);
        messageService.setTest(test);
        messageService.setIPRestrictOnOff(IPRestrictOnOff);
        messageService.setUseStaticIP(useStaticIP);
        messageService.setUseLocalTimeYN(useLocalTimeYN);
        
        // 발신자이름
        String senderName = userID;

        // 메시지 내용, 90Byte초과된 내용은 길이가 조정되어 전송됨
        String content = (String)paramMap.get("SMS_CONTENT");

        // 예약전송일시(yyyyMMddHHmmss), null인 경우 즉시전송
        Date reserveDT = null;

        //  예약전송시 아래의 코드 참조
        //  String reserveDTtxt ="20141230190000";
        //  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        //  reserveDT = formatter.parse(reserveDTtxt);

        // 광고문자 전송여부
        Boolean adsYN = false;

        // 전송요청번호
        // 파트너가 전송 건에 대해 관리번호를 구성하여 관리하는 경우 사용.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        String receiptNum = null;
        
        
        
        Message[] receiverList = new Message[paramList.size()];
        
        int msgIdx = 0;
        
        for(Map<String, Object> userInfo : paramList) {
            // 수신번호
            String receiver = (String)userInfo.get("RMSMN_TELNO");
            
            // 수신자명
            String receiverName = (String)userInfo.get("RMSMN_NM");
            
            Message receiverInfo = new Message();
            receiverInfo.setReceiver(receiver);
            receiverInfo.setReceiverName(receiverName);
            
            receiverList[msgIdx] = receiverInfo;
            msgIdx ++;
        }
        
        try {
            // 단체문자
            receiptNum = messageService.sendSMS(corpNum, sendNum, senderName, content, receiverList, reserveDT, adsYN, userID, requestNum);
            return "OK";
        } catch (PopbillException pe) {
            //적절한 오류 처리를 합니다. pe.getCode() 로 오류코드를 확인하고, pe.getMessage()로 관련 오류메시지를 확인합니다.
            System.out.println("오류코드 " + pe.getCode());
            System.out.println("오류메시지 " + pe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "FAIL";
        
    }
    
    /**
	 * fax 전송
	 * @param paramList01 - fax 수신자 리스트
	 * @param paramList02 - fax 전송 파일
	 * @return void형
	 * @exception Exception
	 */
    @Override
    public String faxSend(List<Map<String, Object>> paramList01, List<Map<String, Object>> paramList02) throws Exception {
        egovLogger.debug("************* FAX 연동 [faxSend] ****************");
        
        FaxServiceImp faxService = new FaxServiceImp();
        
        faxService.setLinkID(linkID);
        faxService.setSecretKey(secretKey);
        faxService.setTest(test);
        faxService.setIPRestrictOnOff(IPRestrictOnOff);
        faxService.setUseStaticIP(useStaticIP);
        faxService.setUseLocalTimeYN(useLocalTimeYN);
        
         /*
         * 팩스를 전송합니다. (전송할 파일 개수는 최대 20개까지 가능)
         */

        // 발신자명
//      String senderName = (String)paramMap.get("SENDER_NM");
        String senderName = "모든솔루션";

        // 수신자 리스트
        Receiver[] receiverList = new Receiver[paramList01.size()];
        
        // 수신자 리스트 인덱스
        int msgIdx = 0;
        
        for(Map<String, Object> userInfo : paramList01) {
            // 수신번호
            String receiverNum = (String)userInfo.get("RMSMN_TELNO");
            
            // 수신자명
            String receiverName = (String)userInfo.get("RMSMN_NM");
            
            Receiver receiverInfo = new Receiver();
            receiverInfo.setReceiveNum(receiverNum);
            receiverInfo.setReceiveName(receiverName);
            
            receiverList[msgIdx] = receiverInfo;
            msgIdx ++;
        }
        

        // 예약전송일시(yyyyMMddHHmmss), null인 경우 즉시전송
        Date reserveDT = null;

        // String reserveDTtxt = "20180726120000";
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        // reserveDT = formatter.parse(reserveDTtxt);
        
        // 파일 전송 개수 최대 20개
        File[] content = new File[paramList02.size()];
        int fileCnt = 0;
        for(Map<String, Object> fileInfo : paramList02) {
        	// 파일경로
        	String filePath = (String)fileInfo.get("FILE_PATH");
        	// 경로 '\\' -> '/' 수정
        	filePath = filePath.replace("\\", "/");
        	content[fileCnt] = new File(filePath);
        	fileCnt ++;
        }
        
        // 광고팩스 전송여부
        Boolean adsYN = false;

        // 팩스제목
        String title = "팩스 전송 테스트";

        // 전송요청번호
        // 파트너가 전송 건에 대해 관리번호를 구성하여 관리하는 경우 사용.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        String receiptNum = null;
        try {
            //receiptNum = faxService.sendFAX(corpNum, sendNum, receiveNum, receiveName, content, reserveDT, userID, adsYN, title, requestNum);
            receiptNum = faxService.sendFAX(corpNum, sendNum, senderName, receiverList, content, reserveDT, userID, adsYN, title, requestNum);
        } catch (PopbillException pe) {
            //적절한 오류 처리를 합니다. pe.getCode() 로 오류코드를 확인하고, pe.getMessage()로 관련 오류메시지를 확인합니다.
            System.out.println("오류코드 " + pe.getCode());
            System.out.println("오류메시지 " + pe.getMessage());
            throw pe;
        }
        
        return "OK";
    }
    
    //카카오 알림톡
    @Override
    public String kakaoSend(LinkedHashMap<String, Object> paramMap, List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************* 카카오 연동 [kakaoSend] ****************");
        
        // 카카오 서비스 객채 생성
        KakaoServiceImp kakaoService = new KakaoServiceImp(); 
        kakaoService = this.kakaoObject(kakaoService);
        
        // 알림톡 템플릿코드
        // 승인된 알림톡 템플릿 코드는 ListATStemplate API, GetATSTemplateMgtURL API, 또는 팝빌사이트에서 확인 가능합니다.
        String templateCode = "021020000070";

        // 수신자 리스트
        KakaoReceiver[] receiverList = new KakaoReceiver[paramList.size()];
        
        // 수신자 리스트 인덱스
        int msgIdx = 0;
        
        for(Map<String, Object> userInfo : paramList) {
            // 수신번호
            String receiverNum = (String)userInfo.get("RECEIVER_NUM");
            
            // 수신자명
            String receiverName = (String)userInfo.get("RECEIVER_NM");
            
            KakaoReceiver receiverInfo = new KakaoReceiver();
            receiverInfo.setReceiverNum(receiverNum);
            receiverInfo.setReceiverName(receiverName);
            
            receiverList[msgIdx] = receiverInfo;
            msgIdx ++;
        }
        
        // 알림톡 내용 (최대 1000자)
        String content = null; 
//      content  = "[모든솔루션]\n";
//      content += "#{담당자이름} 담당자님께 #{업무서류제목}(이)가 왔습니다.\n\n";
//      content += "* 발 신 자 : #{요청자}\n";
//      content += "* 발신 일자 : #{업무처리요청일자}";
        

        // 대체문자 내용 (최대 2000byte)
        String altContent = "확인하실 업무가 있습니다.";

        // 대체문자 전송유형, 공백-미전송, C-알림톡 내용전송, A-대체문자 내용 전송
        String altSendType = "C";
        

        // 예약전송일시, 형태(yyyyMMddHHmmss)
        String sndDT = "";

        // 전송요청번호
        // 파트너가 전송 건에 대해 관리번호를 구성하여 관리하는 경우 사용.
        // 1~36자리로 구성. 영문, 숫자, 하이픈(-), 언더바(_)를 조합하여 팝빌 회원별로 중복되지 않도록 할당.
        String requestNum = "";

        // 알림톡 버튼정보를 템플릿 신청시 기재한 버튼정보와 동일하게 전송하는 경우 null 처리.
        KakaoButton[] btns = null;

        // 알림톡 버튼 URL에 #{템플릿변수}를 기재한경우 템플릿변수 영역을 변경하여 버튼정보 구성
//        KakaoButton[] btns = new KakaoButton[1];
//
//        KakaoButton button = new KakaoButton();
//        button.setN("버튼명"); // 버튼명
//        button.setT("WL"); // 버튼타입
//        button.setU1("https://www.popbill.com"); // 버튼링크1
//        button.setU2("http://test.popbill.com"); // 버튼링크2
//        btns[0] = button;

        String receiverNum = "01058005807";
        String receiverName = "박지환";
        try {

            String receiptNum = kakaoService.sendATS(corpNum, templateCode, sendNum, content, altContent,
                altSendType, receiverNum, receiverName, sndDT, userID, requestNum, btns);
            String receiptNum1 = kakaoService.sendATS(corpNum, templateCode, sendNum, content, altContent,
            		altSendType, receiverList, sndDT, userID, requestNum, btns);
        } catch (PopbillException e) {
            // 예외 발생 시, e.getCode() 로 오류 코드를 확인하고, e.getMessage()로 오류 메시지를 확인합니다.
            System.out.println("오류 코드" + e.getCode());
            System.out.println("오류 메시지" + e.getMessage());
        }
        
        return "OK";
    }
    
    @Override
    public List<?> kakaoTemplate() throws Exception {
    	
    	// 카카오 서비스 객체선언
    	KakaoServiceImp kakaoService = new KakaoServiceImp();
    	kakaoService = this.kakaoObject(kakaoService);
    	
    	// 마이빌더로 던져줄 리스트 객체 선언
    	List<LinkedHashMap<String, Object>> resultList = new ArrayList<LinkedHashMap<String, Object>>();
    	
    	try {
    		// 알림톡 템플릿 리스트
    		ATSTemplate[] templateList = kakaoService.listATSTemplate(corpNum, userID);

			
			// 알림톡 정보 조회
			for(ATSTemplate templateInfo : templateList) {
				
	    		// 알림톡 템플릿 정보를 담을 맵 선언
	    		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
	    		
	    		String templateCode = templateInfo.getTemplateCode();    // 템플릿코드
	    		String templateName = templateInfo.getTemplateName();    // 템플릿이름
	    		String templateContent = templateInfo.getTemplate();     // 템플릿내용
	    		
	    		resultMap.put("TEMPLATE_CODE", templateCode);
	    		resultMap.put("TEMPLATE_NAME", templateName);
	    		resultMap.put("TEMPLATE_CONTENT", templateContent);
	    		resultList.add(resultMap);
			}
    	} catch (PopbillException e) {
    		// 예외 발생 시, e.getCode() 로 오류 코드를 확인하고, e.getMessage()로 오류 메시지를 확인합니다.
    		System.out.println("오류 코드" + e.getCode());
    		System.out.println("오류 메시지" + e.getMessage());
    	}
    	
    	return resultList;
    }
    
    // 카카오 알림톡 template과 내용
    private KakaoServiceImp kakaoObject(KakaoServiceImp kakaoService) {
        kakaoService.setLinkID(linkID);                  
        kakaoService.setSecretKey(secretKey);            
        kakaoService.setTest(test);                      
        kakaoService.setIPRestrictOnOff(IPRestrictOnOff);
        kakaoService.setUseStaticIP(useStaticIP);        
        kakaoService.setUseLocalTimeYN(useLocalTimeYN);
        
    	return kakaoService;
    }
}
