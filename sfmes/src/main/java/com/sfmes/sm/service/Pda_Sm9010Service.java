package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Pda_Sm9010Service.java
 * @Description : PDA 입고등록 인터페이스
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -----------------  --------------
 * @ 2021.10.07  이동훈      최초생성
 *  
 * @author (주)모든솔루션
 * @since 2021.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Pda_Sm9010Service {
    
    //물품 기본 조회 
    List<?> search_Pda_Sm9010_gds(LinkedHashMap paramMap) throws Exception;
    
    //거래처 조회
    List<?> search_Pda_Sm9010_trpl(LinkedHashMap paramMap) throws Exception;
    
    //PDA 저장
    List<?> insertinfopda(LinkedHashMap paramMap) throws Exception;
    
  //PDA 박스이동
    List<?> insertmovebox(LinkedHashMap paramMap) throws Exception;
    
  //PDA 오프라인 저장
    List<?> insertoffdata(LinkedHashMap paramMap) throws Exception;
    
    //PDA 지시번호 조회
    List<?> selectEmesbarcode(LinkedHashMap paramMap) throws Exception;
    
    //PDA 사업장 기존 창고 조회
    List<?> search_Pda_Sm9010_whse(LinkedHashMap paramMap) throws Exception;
    
  //PDA 사업장  창고 조회
    List<?> search_Pda_Sm9010_whse_list(LinkedHashMap paramMap) throws Exception;
    
    //PDA 사업장  창고명 조회
    List<?> search_Pda_Sm9010_whse_nm(LinkedHashMap paramMap) throws Exception;
    
  //PDA 입고등록 박스바코드 조회
    List<?> selectTmfbarcode(LinkedHashMap paramMap) throws Exception;
    
  //PDA 제품출고 박스바코드 조회
    List<?> selectgetprddata(LinkedHashMap paramMap) throws Exception;
    
  //PDA 투입지시 박스바코드 조회
    List<?> selectgetboxdata(LinkedHashMap paramMap) throws Exception;
    
  //PDA 입고반품 박스바코드 조회
    List<?> selectgetpucboxdata(LinkedHashMap paramMap) throws Exception;
    
  //PDA 창고이동 박스바코드 조회
    List<?> selectgetmoveboxdata(LinkedHashMap paramMap) throws Exception;
    
  //PDA 생산투입할당 박스바코드 조회
    List<?> selectgetboxdataO5(LinkedHashMap paramMap) throws Exception;
    
  //PDA 박스코드정리 박스조회
    List<?> selectcollectboxdata(LinkedHashMap paramMap) throws Exception;
    
}
