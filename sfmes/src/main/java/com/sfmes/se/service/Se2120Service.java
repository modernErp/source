package com.sfmes.se.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Se2120Service.java
 * @Description : Se2120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.09.16   김지혜     최초생성
 * @ 2022.04.18   나명우     수정
 *
 * @author (주)모든솔루션
 * @since 2020.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Se2120Service {
    
    //수주대비(미)출고현황 조회
    List<?> selectSe2120(LinkedHashMap paramMap) throws Exception;
    
    //수주대비(미)출고대상 조회 2022.04.19 나명우 추가
    List<?> selectSe2120_02(LinkedHashMap paramMap) throws Exception;
    
    //미출고 내용 수정 2022.04.19 나명우 추가
    String updateSe2120(LinkedHashMap paramMap) throws Exception;
}
