package com.sfmes.ca.service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Class Name : Ca4110Service.java
 * @Description : Ca4110Service Class
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
public interface Ca4110Service {

    // 외상매입금거래내역
	List<?> selectCa4110List(LinkedHashMap paramMap) throws Exception;
}