package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Co1000Service.java
 * @Description : Co1000Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.09.04   여다혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.09.04
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Co1000Service {
    
    //회사조회(1건)  
    List<?> selctCorpInfo(LinkedHashMap paramMap) throws Exception;
    
    //사업장조회(n건)
    List<?> selectBzplList(LinkedHashMap paramMap) throws Exception;
    
    //회사정보수정
    void updateCo1000(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
