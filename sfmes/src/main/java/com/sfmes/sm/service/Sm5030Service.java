
package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm5030Service.java
 * @Description : Sm5030Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.28.  정성환      최조작성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm5030Service {
    
    //재고실사조정등록 기본 조회 
    List<?> searchSm5030_01(LinkedHashMap paramMap) throws Exception;
    
    //재고실사조정등록 확정취소 
    String updateSm5030_01(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
  //재고실사조정등록 확정 
    String updateSm5030_02(LinkedHashMap paramMap) throws Exception;
    
}
