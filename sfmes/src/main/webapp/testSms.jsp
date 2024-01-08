<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Popbill Message Example</title>
    </head>
<%@ include file="common2.jsp" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.popbill.api.PopbillException"%>
<%

    /*
     * SMS(단문)를 전송합니다.
     * - 메시지 내용이 90Byte 초과시 메시지 내용은 자동으로 제거됩니다.
     */
    // 팝빌회원 사업자번호
    String testCorpNum = "2648149853";

    // 팝빌회원 아이디
    String testUserID = "MODERNSOLU";

    // 발신번호
    String sender = "0313607870";

    // 수신번호
    String receiver = "01038275198";

    // 수신자명
    String receiverName = "신현봉";

    // 메시지 내용, 90Byte초과된 내용은 길이가 조정되어 전송됨
    String content = "E-MES 문자전송 테스트 입니다.";

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
    try {
        receiptNum = messageService.sendSMS(testCorpNum, sender, receiver, receiverName, content, reserveDT, adsYN, testUserID, requestNum);
    } catch (PopbillException pe) {
        //적절한 오류 처리를 합니다. pe.getCode() 로 오류코드를 확인하고, pe.getMessage()로 관련 오류메시지를 확인합니다.
          System.out.println("오류코드 " + pe.getCode());
          System.out.println("오류메시지 " + pe.getMessage());
        // throw pe;
    } catch (Exception e) {
    	e.printStackTrace();
    }
%>
    <body>
        <p>Response</p>
        <br/>
        <fieldset>
            <legend>SMS 문자 전송</legend>
            <ul>
                <li>receiptNum (접수번호) :<%=receiptNum%></li>
            </ul>
        </fieldset>
    </body>
</html>