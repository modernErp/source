<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Popbill FAX Example</title>
    </head>
<%@ include file="common.jsp" %>
<%@page import="java.io.File" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="com.popbill.api.PopbillException" %>
<%

    /*
     * 팩스를 전송합니다. (전송할 파일 개수는 최대 20개까지 가능)
     */

    // 팝빌회원 사업자번호
    String testCorpNum = "2648149853";

    // 팝빌회원 아이디
    String testUserID = "MODERNSOLU";

    // 발신번호
    String sendNum = "0313607870";

    // 발신자명
    String senderName = "모든솔루션";

    // 수신자명
    String receiveName = "모든팩토리";

    // 수신 팩스번호
    String receiveNum = "03180697870";

    // 예약전송일시(yyyyMMddHHmmss), null인 경우 즉시전송
    Date reserveDT = null;

    // String reserveDTtxt = "20180726120000";
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    // reserveDT = formatter.parse(reserveDTtxt);
    // 팩스전송파일 경로
    // 파일 전송 개수 최대 20개
    File[] files = new File[1];
    // files[0] = new File((application.getRealPath("/resources/test.jpg")));
    files[0] = new File("C:/test.pdf");
    //files[1] = new File("C:/test.pdf");

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
        receiptNum = faxService.sendFAX(testCorpNum, sendNum, receiveNum, receiveName, files, reserveDT, testUserID, adsYN, title, requestNum);
    } catch (PopbillException pe) {
        //적절한 오류 처리를 합니다. pe.getCode() 로 오류코드를 확인하고, pe.getMessage()로 관련 오류메시지를 확인합니다.
        System.out.println("오류코드 " + pe.getCode());
        System.out.println("오류메시지 " + pe.getMessage());
        throw pe;
    }
%>
    <body>
        <p>Response</p>
        <br/>
        <fieldset>
            <legend>팩스 전송 요청</legend>
            <ul>
                <li>receiptNum (접수번호) :<%=receiptNum%></li>
            </ul>
        </fieldset>
    </body>
</html>