package com.sfmes.dl.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Dl1110Service.java
 * @Description : Dl1110Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.12.07   이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.12.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Dl1110Service {
    
    // 회계전표집계표
    List<?> selectDl1110List(LinkedHashMap paramMap) throws Exception;
    
}