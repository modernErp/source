package com.sfmes.co.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name : Co1090Service.java
 * @Description : Co1090Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.08.06  이수빈      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.08.06
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
public interface Co1090Service {

	void insertCo1090(LinkedHashMap paramMap) throws Exception;
	
	/**
	 * 사업장 수정
	 * @param paramMap - 등록할 정보가 담긴 Map
	 * @return void형
	 * @exception Exception
	 */
	
	void updateCo1090(LinkedHashMap paramMap) throws Exception;
	
	
	/**
	 * 테스트 데이터를 목록조회한다.
	 * @param paramMap - 조회할 조건이 담긴 Map
	 * @return 결과 목록
	 * @exception Exception
	 */
	List<?> selectCo1090List(LinkedHashMap paramMap) throws Exception;
}