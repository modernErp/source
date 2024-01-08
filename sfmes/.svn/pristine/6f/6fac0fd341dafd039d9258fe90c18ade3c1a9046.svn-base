package com.sfmes.cm.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @Class Name  : MultiPartController.java
 * @Description : CommonMultiPart Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ----------   ------   -------------------------------
 * @ 2020.08.12   여다혜   최초작성
 *
 * @author (주)모든솔루션
 * @since 2020.08.12
 * @version 1.0
 * @see
 *
 *  Copyright (C) by (주)모든솔루션 All right reserved.
 */

@Controller
public class MultiPartController {

    @Resource(name = "myBuilderData")
    protected MyBuilderData myBuilderData;

    /**
     * 파일업로드
     * @param 
     * @return 
     * @exception 
     */
    @RequestMapping(value = "/uploadController_old.do")
    public String uploadController( HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        
        System.out.println("===== uploadController!!!! =====");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        java.util.Iterator<String> fileNames = multipartRequest.getFileNames();
        
        System.out.println("===== fileNames!!!! =====" + fileNames.toString());
        
        while(fileNames.hasNext()){

        	// 전송된 파일명을 추출한다
            String fileName = fileNames.next();
            
            // 특정 파일을 생성한다.
            MultipartFile mFile = multipartRequest.getFile(fileName);
            
            // 대상 파일의 원본 파일명을 추출한다.
            String OriginalFilename = mFile.getOriginalFilename();
            
            String uploadPath = request.getServletContext().getRealPath("/upload");
            
            System.out.println(uploadPath + "<<<<< updaloadPath >>>>> ");
            
            // 전송한 파일을 서버에 생성한다.
            // File file = new File("C:\\sfmes\\Upload", OriginalFilename);
            File file = new File(uploadPath, OriginalFilename);

            if(mFile.getSize()!=0){ //File Null Check
                if(!file.exists()){ //경로상에 파일이 존재하지 않을 경우
                    if(file.getParentFile().mkdirs()){ //경로에 해당하는 디렉토리들을 생성
                        try{
                            file.createNewFile(); //이후 파일 생성

                        }catch(IOException e){
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    mFile.transferTo(file); //임시로 저장된 multipartFile을 실제 파일로 전송
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return "responseToMybuilder";
    }
    
}
