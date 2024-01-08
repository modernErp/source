package com.sfmes.mi.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name  : Mi1120Service.java
 * @Description : Mi1120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.11.12   곽환용    최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Mi1120Service {
    
    //기간대비매출현황
    List<?> selectMi1120_01(LinkedHashMap paramMap) throws Exception;
}
