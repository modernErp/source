package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Sy1035Service.java
 * @Description : Sy1035Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.01  김수민      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.01
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sy1035Service {
    
    /**
     * 사용자내역(사용자찾기팝업) 조회
     */
    List<?> selectSy1035List(LinkedHashMap paramMap) throws Exception;


}
