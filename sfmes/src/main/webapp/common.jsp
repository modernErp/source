<%-- 팩스 클래스 빈 생성 --%>
<jsp:useBean id="faxService" scope="application" class="com.popbill.api.fax.FaxServiceImp" />

<%-- 링크아이디 --%>
<jsp:setProperty name="faxService" property="linkID" value="MODERNSOLU" />

<%-- 비밀키, 사용자 인증에 사용되는 정보이므로 유출에 주의 --%>
<jsp:setProperty name="faxService" property="secretKey" value="YtbAbisTFmPrlRTO40lTwYb4kJdaFVglQjKKb5CMhkU=" />

<%-- 연동환경 설정값, 개발용(true), 상업용(false) --%>
<jsp:setProperty name="faxService" property="test" value="true" />

<%-- 인증토큰 발급 IP 제한 On/Off, ture -제한기능 사용(기본값-권장), false-제한기능 미사용 --%>
<jsp:setProperty name="faxService" property="IPRestrictOnOff" value="true" />

<%-- 팝빌 API 서비스 고정 IP 사용여부(GA), true-사용, false-미사용, 기본값(false) --%>
<jsp:setProperty name="faxService" property="useStaticIP" value="false"/>

<%-- 로컬서버 시간 사용여부 true-사용(기본값-권장), false-미사용 --%>
<jsp:setProperty name="faxService" property="useLocalTimeYN" value="true"/>

