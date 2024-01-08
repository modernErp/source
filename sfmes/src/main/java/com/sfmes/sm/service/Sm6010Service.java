package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm6010Service.java
 * @Description : Sm6010Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.05  정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm6010Service {

    //재고_원장계수정정기본 (신규등록을 위한 일련번호 채번 호출) 조회
    List<?> searchSm6010_01(LinkedHashMap paramMap) throws Exception;
    
    //품원장계수정정 등록
    void insertSm6010_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
}
