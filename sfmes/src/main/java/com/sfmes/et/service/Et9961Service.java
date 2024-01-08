package com.sfmes.et.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Et9961Service.java
 * @Description : Et9961Service Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2022.09.16   김주경     최초생성
 *
 * @author (주)모든솔루션
 * @since 2022.09.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Et9961Service {
    
    /**
     * 사용자내역(사용자찾기팝업) 조회
     */    
    List<?> selectEt9961List(LinkedHashMap paramMap) throws Exception;

}
