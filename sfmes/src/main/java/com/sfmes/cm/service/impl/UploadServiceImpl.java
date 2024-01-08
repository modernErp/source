package com.sfmes.cm.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sfmes.cm.service.UploadService;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.cm.web.LoginVO;

/**
 * @Class Name  : UploadServiceImpl.java
 * @Description : 첨부파일 관련 Service Impl
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.10.07   여다혜      최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.07
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("UploadService")
public class UploadServiceImpl extends CmnAbstractServiceImpl implements UploadService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    //첨부파일 등록
    @Override
    public String insertUploadFile(HttpServletRequest request) throws Exception {
        
        System.out.println("impl 시작");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        java.util.Iterator<String> fileNames = multipartRequest.getFileNames();
        
        //session값
        HttpSession session = request.getSession();
        LoginVO loginObj = (LoginVO)session.getAttribute("LOGIN_INFO");
        
        //파일 저장 시 필요한 Column
        /* NO    COLUMN            COLUMN COMMENT
         * ==========================================
         * 1     CORP_C            회사코드            session정보 / request
         * 2     APD_FILE_AMN_NO   첨부파일관리번호    채번        / query
         * 3     APD_FILE_SQNO     첨부파일일련번호    반복분      / while
         * 4     APD_FILE_DSC      첨부파일구분코드    N/A     
         * 5     STOG_PATH         저장경로            session정보 / request
         * 6     STOG_FILE_NM      저장파일명          채번+반복분 / while
         * 7     OTXT_FILE_NM      원본파일명          반복분      / while
         * 8     XTN_NM            확장자명            반복분      / while 
         * 9     FILE_SZE          파일크기            반복분      / while
         *   
         */
        
        //파일 저장 전, 파일관리번호 채번 
        LinkedHashMap<String, Object> paramMapForAutoNo = new LinkedHashMap<String, Object>(); //채번용 paramMap
        paramMapForAutoNo.put("CORP_C", loginObj.getCORP_C());
        paramMapForAutoNo.put("gbnTaskDsc", request.getParameter("gbnTaskDsc"));
        String APD_FILE_AMN_NO = sqlSession.selectOne("sfmes.sqlmap.cm.selectUploadFileNo", paramMapForAutoNo); //채번생성 쿼리
        
        //첨부파일 저장용 paramMap 할당
        LinkedHashMap<String, Object> paramMapForInsert = new LinkedHashMap<String, Object>(); //첨부파일테이블 저장용 paramMap
        paramMapForInsert.put("CORP_C", loginObj.getCORP_C());      //회사코드
        paramMapForInsert.put("APD_FILE_AMN_NO", APD_FILE_AMN_NO);  //첨부파일관리번호
        paramMapForInsert.put("STOG_PATH", request.getServletContext().getRealPath("/upload/" + loginObj.getCORP_C())); //파일저장 할 경로
        paramMapForInsert.put("GUSRID", loginObj.getUSR_ID());      //최초등록자, 최종변경자
        
        int i_SQNO = 0; //3
        
        while(fileNames.hasNext()){
            i_SQNO += 1; //SQNO CNT
            
            // 전송된 파일명을 추출한다
            String fileName = fileNames.next();
            
            // 특정 파일을 생성한다.
            MultipartFile mFile = multipartRequest.getFile(fileName);

            // 특정 파일 정보를 paramMap에 put함 (개별 정보)
            paramMapForInsert.put("APD_FILE_SQNO"  , i_SQNO); //파일sequence 
            paramMapForInsert.put("STOG_FILE_NM"   , paramMapForInsert.get("APD_FILE_AMN_NO") + "_" + Integer.toString(i_SQNO)); //서버에 저장될 파일명(관리용 파일명)
            paramMapForInsert.put("OTXT_FILE_NM"   , mFile.getOriginalFilename()); //원본파일명
            paramMapForInsert.put("XTN_NM"         , mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".")+1)); //확장자
            paramMapForInsert.put("FILE_SZE"       , mFile.getSize() + ""); //파일사이즈

            //테이블에 파일 저장
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_APDFL", paramMapForInsert); 
            
            //파일 Object 생성
            File file = new File((String)paramMapForInsert.get("STOG_PATH"), (String)paramMapForInsert.get("STOG_FILE_NM") + "." + paramMapForInsert.get("XTN_NM"));
           
            if(mFile.getSize()!=0){ //File Null Check
                if(!file.exists()){ //경로상에 파일이 존재하지 않을 경우
                    if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
                        try{
                            file.createNewFile(); //이후 파일 생성

                        }catch(IOException e){
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            throw infoException("create New File Error : IOException");
                        }
                    }
                }

                try {
                    mFile.transferTo(file); //임시로 저장된 multipartFile을 실제 파일로 전송
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw infoException("transferTo Error : IllegalStateException");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw infoException("transferTo Error : IOException");
                }
            }
        }
        
        return APD_FILE_AMN_NO;

    }

    //등록된 첨부파일을 uploadKey(APD_FILE_AMN_NO)로 조회한다
    @Override
    public List<?> selectUploadFileList(LinkedHashMap paramMap) throws Exception {
        return sqlSession.selectList("sfmes.sqlmap.tb.select_TB_CO_M_APDFL", paramMap);
    }
}
