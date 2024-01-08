package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca4120Service.java
 * @Description : Ca4120Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.07.14  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.07.14
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Ca4120Service {

    // 외상매입금일원장조회
	List<?> selectCa4120List(LinkedHashMap paramMap) throws Exception;
}