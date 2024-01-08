
package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm5040Service.java
 * @Description : Sm5040Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.29.  정성환      최조작성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm5040Service {
    
    //재고실사확정등록 기본 조회 
    List<?> searchSm5040_01(LinkedHashMap paramMap) throws Exception;
    
    //재고실사확정등록 확정취소 
    String updateSm5040_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
}
