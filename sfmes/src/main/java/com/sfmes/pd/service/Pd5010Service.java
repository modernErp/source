package com.sfmes.pd.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
* @Class Name : Pd5010Service.java
* @Description : Pd5010Service Class
* @Modification Information
* @
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.10.16   박지환     최초생성
*
* @author (주)모든솔루션
* @since 2020.10.16
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/

public interface Pd5010Service {
    
	/**
     * 수탁가공접수 등록
     */
	String insertPd5010(LinkedHashMap paramMap) throws Exception;

    /**
     * 수탁가공접수 수정
     */
    void updatePd5010(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수탁가공접수 삭제
     */
    void deletePd5010(LinkedHashMap paramMap) throws Exception;
    
    /**
     * 수탁가공접수 조회
     */
    List<?> selectPd5010(LinkedHashMap paramMap) throws Exception;
    
}
