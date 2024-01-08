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
import com.sfmes.cm.service.UserUseLogService;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.cm.web.LoginVO;

/**
 * @Class Name  : UserUseLogServiceImpl.java
 * @Description : UserUseLogServiceImpl Class
 * @Modification Information
 * @
 * @  수정일      수정자     수정내용
 * @ ----------  --------   -------------------------------
 * @ 2020.10.02   이철홍     최초생성
 *
 * @author (주)모든솔루션
 * @since 2020.10.02
 * @version 1.0
 * @see
 *
 *  Copyright (C) by 모든솔루션 All right reserved.
 */
@Service("UserUseLogService")
public class UserUseLogServiceImpl extends CmnAbstractServiceImpl implements UserUseLogService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 사용자 시스템 사용 이력을 저장한다.
     * @param paramMap - 등록할 정보가 담긴 Map
     * @return void형
     * @exception Exception
     */
    @Override
    public void insertUseLog(LinkedHashMap paramMap) throws Exception {
        sqlSession.insert("sfmes.sqlmap.tb.insert_TB_SY_L_PGM_UG", paramMap); 
    }
}
