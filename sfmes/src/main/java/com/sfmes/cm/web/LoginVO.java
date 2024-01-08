/**
 * 
 */
package com.sfmes.cm.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Class Name   : LoginVo.java
 * @Description  : 사용자세션정보 클래스
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.15  여다혜      최초생성
 * @ 2021.10.22  서광석      대표사업장명(BZPL_NM), 관리자여부(ADMIN_YN) 항목 추가
 * @ 2021.12.14  여다혜      LocalDate 클래스 추가, 서버시간 리턴용
 *
 * @since 2020.06.15
 * @version 1.0
 * @see
 */
public class LoginVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8690793271537569168L;

    private String CORP_C           ;   /* 회사코드             */ 
    private String CORP_NM          ;   /* 회사명               */
    private String USR_ID           ;   /* 사용자아이디         */   
    private String USR_NM           ;   /* 사용자명             */ 
    private String BZPL_C           ;   /* 사업장코드           */
    private String BZPL_NM          ;   /* 사업장명             */
    private String DEPT_C           ;   /* 부서코드             */ 
    private String PZC_C            ;   /* 직급코드             */ 
    private String OFT_C            ;   /* 직명코드             */ 
    private String USE_DSC          ;   /* 사용자구분           */  
    private String LS_CONN_IP       ;   /* 최종IP               */
    private String CONN_YN          ;   /* 접속여부             */ 
    private String ACC_ST_DT        ;   /* 계정시작일자         */   
    private String ACC_ED_DT        ;   /* 계정종료일자         */
    private String ADMIN_YN         ;   /* 관리자여부           */ 
    private String EMP_ROL_DSC     ;   /* 사용자역할구분      */  //20220325 나명우추가
    private String SVR_DATE         ;   /* 서버시간용           */ //20211214 여다혜 추가
    
    public String getCORP_C() {
        return CORP_C;
    }
    public void setCORP_C(String cORP_C) {
        CORP_C = cORP_C;
    }
    public String getUSR_ID() {
        return USR_ID;
    }
    public void setUSR_ID(String uSR_ID) {
        USR_ID = uSR_ID;
    }
    public String getUSR_NM() {
        return USR_NM;
    }
    public void setUSR_NM(String uSR_NM) {
        USR_NM = uSR_NM;
    }
    public String getBZPL_C() {
        return BZPL_C;
    }
    public void setBZPL_C(String bZPL_C) {
        BZPL_C = bZPL_C;
    }
    public String getBZPL_NM() {
        return BZPL_NM;
    }
    public void setBZPL_NM(String bZPL_NM) {
        BZPL_NM = bZPL_NM;
    }    
    public String getDEPT_C() {
        return DEPT_C;
    }
    public void setDEPT_C(String dEPT_C) {
        DEPT_C = dEPT_C;
    }
    public String getPZC_C() {
        return PZC_C;
    }
    public void setPZC_C(String pZC_C) {
        PZC_C = pZC_C;
    }
    public String getOFT_C() {
        return OFT_C;
    }
    public void setOFT_C(String oFT_C) {
        OFT_C = oFT_C;
    }
    public String getUSE_DSC() {
        return USE_DSC;
    }
    public void setUSE_DSC(String uSE_DSC) {
        USE_DSC = uSE_DSC;
    }
    public String getLS_CONN_IP() {
        return LS_CONN_IP;
    }
    public void setLS_CONN_IP(String lS_CONN_IP) {
        LS_CONN_IP = lS_CONN_IP;
    }
    public String getCONN_YN() {
        return CONN_YN;
    }
    public void setCONN_YN(String cONN_YN) {
        CONN_YN = cONN_YN;
    }
    public String getACC_ST_DT() {
        return ACC_ST_DT;
    }
    public void setACC_ST_DT(String aCC_ST_DT) {
        ACC_ST_DT = aCC_ST_DT;
    }
    public String getACC_ED_DT() {
        return ACC_ED_DT;
    }
    public void setACC_ED_DT(String aCC_ED_DT) {
        ACC_ED_DT = aCC_ED_DT;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getCORP_NM() {
        return CORP_NM;
    }
    public void setCORP_NM(String cORP_NM) {
        CORP_NM = cORP_NM;
    }
    public String getADMIN_YN() {
        return ADMIN_YN;
    }
    public void setADMIN_YN(String aDMIN_YN) {
        ADMIN_YN = aDMIN_YN;
    }    
    
    public String getSVR_DATE() {
        return SVR_DATE;
    }
    
    
    public String getEMP_ROL_DSC() {
        return EMP_ROL_DSC;
    }
    public void setEMP_ROL_DSC(String eMP_ROL_DSC) {
        EMP_ROL_DSC = eMP_ROL_DSC;
    }
    public void setSVR_DATE(String sVR_DATE) {
        /*
        LocalDate serverDate = LocalDate.now();  //날짜정보
        SVR_DATE = serverDate.format(DateTimeFormatter.BASIC_ISO_DATE); //YYYYmmdd 포맷
        
        System.out.println("setter, SVR_DATE ::" + SVR_DATE);
        */
        
        SVR_DATE = sVR_DATE;
        
    }
}
