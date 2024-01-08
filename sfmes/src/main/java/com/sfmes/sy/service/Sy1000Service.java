package com.sfmes.sy.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class Name  : Sy1000Service.java
 * @Description : Sy1000Service Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.06.24   김지혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.06.24
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */

public interface Sy1000Service {
    
    //회사신규등록
    void insertCorp_C(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    //회사정보수정
    void updateCorp_C(LinkedHashMap paramMap, List<Map<String, Object>> paramList) throws Exception;
    
    // 회사환경 초기 설정
    void insertSy1000(LinkedHashMap paramMap) throws Exception;
    
    // 회사환경 삭제
    void deleteSy1000(LinkedHashMap paramMap) throws Exception;
    
    //회사등록화면조회(SY1000.mvf)  
    List<?> selctDetailSy1000(LinkedHashMap paramMap) throws Exception;
    
    //회사내역화면조회(SY1005.mvf)
    List<?> selectSy1005(LinkedHashMap paramMap) throws Exception;

    //사업장그리드조회
    List<?> select_bzpl(LinkedHashMap paramMap) throws Exception;

}
