package com.sfmes.sm.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sm5045Service.java
 * @Description : Sm5045Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.11.02  정성환      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sm5045Service {
    //재고실사확정내역
    List<?> searchSm5045_01(LinkedHashMap paramMap) throws Exception;
    
    //재고실사-전표삭제 및 실사삭제처리
    void deleteSm5045_01(LinkedHashMap paramMap) throws Exception;
}
