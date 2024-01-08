package com.sfmes.cm.service;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 파일업로드 처리 인터페이스 
 * 
 * @author 
 * @since 2020.10.07
 * @version 1.0
 * @see
 * 
 * 
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  ----------   ------    ---------------------------
 *  2020.10.07   여다혜    최초작성 
 */
public interface UploadService {
    
    //파일업로드
    String insertUploadFile(HttpServletRequest request) throws Exception;
    
    List<?> selectUploadFileList(LinkedHashMap paramMap) throws Exception;
}
