package com.sfmes.co.service.impl;

import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.XML;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
// org sourrce 20200911 JKS
//import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.stereotype.Service;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;

import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CoSraNoHistService;
import com.sfmes.pd.service.IF_PD_SM_HST_MNGService;
import com.sfmes.co.service.Co2030Service;

/**
* @Class Name : Co2030ServiceImpl.java
* @Description : Co2030Service Class
* @Modification Information
* @ openapi 통해 축산물이력(묶음)번호 정보 가져오기
* @  수정일      수정자              수정내용
* @ ----------  ---------   -------------------------------
* @ 2020.09.09   장경석     최초생성
*
* @author (주)모든솔루션
* @since 2020.09.09
* @version 1.0
* @see
*
*  Copyright (C) by 모든솔루션 All right reserved.
*/
@Service("CoSraNoHistService")
public class CoSraNoHistServiceImpl extends CmnAbstractServiceImpl implements CoSraNoHistService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private Co2030Service co2030Service;
    
    // 공공데이타(www.data.go.kr) openapi 통해 축산물이력(묶음)번호 정보 가져오기
    private static final String dataAnimalTraceUrl = "http://data.ekape.or.kr/openapi-data/service/user/animalTrace/traceNoSearch";//?_wadl&type=xml

    // 수입출산물이력관리(www.meatwatch.go.kr) openapi 통해 수입축산물이력(묶음)번호 정보 가져오기
    private static final String meatAnimalTraceUrl = "http://www.meatwatch.go.kr/rest"; //rest방식 - json

    // 사용자 인증키
    private static final String svcrKey = "U5fQaZ7kgq2iTivqH8W1mHOAsYgP6oozvMDQKUISNy%2Bs8G8jYj8KrYzvNcS83m0rQbCf%2Bw2KAg7lqGqkjDMV6Q%3D%3D"; //인증키
    
    // 수입축산물 (www.meatwatch.go.kr) openapi 사용자 ID
    private static final String sys_id = "test2000";
    
    // 축산물이력(묶음)번호 내역 조회
    @Override
    public LinkedHashMap<String, Object> CoSraNoHistselect(LinkedHashMap paramMap01) throws Exception {
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 축산물이력번호관리기본관리 또는 묶음번호 자료 등록[CoSraNoHist] *********");
        egovLogger.debug("paramMap01: "+paramMap01.toString());
        
        LinkedHashMap<String, Object> resultMap     = new LinkedHashMap<>();
        LinkedHashMap<String, Object> rst_ParamMap  = new LinkedHashMap<>();
        
        try {
        
            String result = "";
            //List<Map<String, Object>> resultList01 = new ArrayList<Map<String, Object>>();
            
            // 공공데이타 자료 확인
            rst_ParamMap = ConnectDataHistNo(paramMap01);
            
            // data 가 존재하는지 확인한다.
            // traceNoType : CATTLE|LOT_NO  or  PIG|LOT_NO 로 구분
            // 첫번째 item 의 infoType = 8일경우 묶음번호
            //             infoType = 1일경우 개체정보
            String sub_str = paramMap01.get("GDS_HST_NO").toString();
            char sub_HstNo[] = sub_str.toCharArray();
            
            egovLogger.debug("sub_HstNo  ::: "+sub_HstNo[0]);
            
            if ("".equals(rst_ParamMap.get("data")) || rst_ParamMap.get("data") != null) {
                if ('L' == sub_HstNo[0]) {

                    // 묶음번호 등록
                    resultMap = CoDataBudlNoHistInsert(paramMap01, rst_ParamMap);                    
                } else {

                    // 개별축산물이력번호 등록
                    resultMap = CoDataIdntNoHistInsert(paramMap01, rst_ParamMap);
                }
            }
            
 
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
 
        return resultMap;
    } 
        
/*        
        // JSON 을 XML 로 변환
        //String xmlJSONObjString = new XMLSerializer().write(xmlJSONObj);
        
        ObjectMapper objMapper = new ObjectMapper();
        
        map = ObjectMapper.readValue(xmlJSONObjString, new TypeReference<Map<String, Object>>(){});
        
//        result = sqlSession.selectOne("sfmes.sqlmap.co.co2030validCheck_01", paramMap01);
//        egovLogger.debug("Validation Check Message : " + result);
//        
//        if("OK".equals(result))
//        {
//            sqlSession.update("sfmes.sqlmap.co.update_co2030_01",paramMap01);
//            
//            return "OK";            
//        }
//        else
//        {
//            return result;
//        }
*/        
//        return sqlSession.selectList("sfmes.sqlmap.co.selectCo2030_List01",paramMap01);

    @Override
    public void CoSraNoHistupdate(LinkedHashMap paramMap01) throws Exception  {
        //축산물이력(묶음)번호기본 내역 수정
        sqlSession.update("sfmes.sqlmap.pd.update_IF_PD_SM_HST_MNG",paramMap01);
               
    }
    
    // 국내산 - 축산물이력(묶음)번호 내역 조회 (공공데이타접속)
    @Override
    public LinkedHashMap<String, Object> ConnectDataHistNo(LinkedHashMap paramMap) throws Exception {
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 국내산 - 축산물이력(묶음)번호 내역 조회 (공공데이타접속)[connectDataHistNo] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        
        try {
            // 공공데이타 OPENAPI 연결
            String opt_dsc = "";
            String srsHisNo = (String) paramMap.get("GDS_HST_NO");  // 조회하고자하는 축산물이력(묶음)번호
    
            StringBuilder sb = new StringBuilder();
            StringBuilder urlBuilder = new StringBuilder(dataAnimalTraceUrl); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + svcrKey); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("traceNo","UTF-8") + "=" + URLEncoder.encode(srsHisNo, "UTF-8")); /*이력번호*/
            urlBuilder.append("&" + URLEncoder.encode("optionNo","UTF-8") + "=" + URLEncoder.encode(opt_dsc, "UTF-8")); /*정보구분자*/
            urlBuilder.append("&corpNo=&");
            egovLogger.debug("URL : " + urlBuilder.toString());
            
            URL url = new URL(urlBuilder.toString());
            
            /* 공공데이타 OPENAPI 연결 */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            egovLogger.debug("Response code: " + conn.getResponseCode());
            
            BufferedReader rd;
            
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));                
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
    
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
                egovLogger.debug("### rd.line=>"+line);
            }
            rd.close();
            
            // 받아온 xml데이타를 JSON 형태로 파싱
            org.json.JSONObject xmlJSONObj = XML.toJSONObject(sb.toString());
            String xmlJSONObjString = xmlJSONObj.toString();
            egovLogger.debug("### xmlJSONObjString=>"+xmlJSONObjString);
            
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();
            
            map = objectMapper.readValue(xmlJSONObjString, HashMap.class);
            Map<String, Object> dataResponse = (Map<String, Object>) map.get("response");
            Map<String, Object> header = (Map<String, Object>) dataResponse.get("header");
            Map<String, Object> body = (Map<String, Object>) dataResponse.get("body");
            Map<String, Object> items = null;
            List<Map<String, Object>> itemList = null; 
            
            items = (Map<String, Object>) body.get("items");
            itemList = (List<Map<String, Object>>) items.get("item");
 
            egovLogger.debug("### map="+map);
            egovLogger.debug("### dataResponse="+dataResponse);
            egovLogger.debug("### body="+body);
            egovLogger.debug("### items="+items);
            egovLogger.debug("### itemList="+itemList);
 
            resultMap.put("resultCode", header.get("resultCode"));
            resultMap.put("resultMsg", header.get("resultMsg"));
            resultMap.put("data", itemList);
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
        
        return resultMap;        
    }

    // 국내산 - 축산물묶음번호 내역 조회 On-line
    @Override
    public LinkedHashMap<String, Object> CoDataBudlNoHistInsert(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        
        // 묶음번호 정보 입력을 위한  List 선언
        List<Map<String, Object>> m_xml2JsonList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> d_xml2JsonList = new ArrayList<Map<String, Object>>();
        //LinkedHashMap<String, Object> budl_map = new LinkedHashMap<>();
        Map<String, Object> m_budl_map = new HashMap<>(); 
        
        String infoType = "";
        String sra_grd_c = "";
        Integer sra_cnt = 0;
        
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 축산물묶음번호  자료 등록[CoDataBudlNoHist] *********");
        egovLogger.debug("paramMap01: "+paramMap01.toString());
        
        try 
        {
            List<Map<String, Object>> itemList = null; 
            itemList = (List<Map<String, Object>>) paramMap01.get("data");
            
            for (Map<String, Object> budl_map : itemList) {

                // 정보타입(infoType) in '1','3','8','9' 만 정보추출한다.
                infoType = budl_map.get("infoType").toString();
                
                if (!"8".equals(infoType) && !"9".equals(infoType)) {
                    continue;
                }
                
                budl_map.put("CORP_C"              , paramMap.get("CORP_C"));
                budl_map.put("BZPL_C"              , paramMap.get("BZPL_C"));
                budl_map.put("GUSRID"              , paramMap.get("GUSRID"));

                // 축종구분
                String sra_dsc = budl_map.get("traceNoType").toString();
                String t_Type = sra_dsc.substring(0, sra_dsc.lastIndexOf("|"));                    

                if ("8".equals(infoType)) {
                    egovLogger.debug("### infoType  =>"+infoType);                        
                    
                    budl_map.put("BUDL_NO"             , budl_map.get("lotNo"));
                    budl_map.put("BUDL_NO_GRD_OPNP_YN" , "N");
                    budl_map.put("BUDL_NO_GRD_DSC"     , "");
                    budl_map.put("RE_BUDL_YN"          , "N");   // 재묶음여부
                    budl_map.put("RPT_DSC"             , "");
                    budl_map.put("RPT_YN"              , "Y");
                    budl_map.put("BUDL_TOT_WT"         , paramMap.get("STDV_WT"));                
                    budl_map.put("USE_YN"              , "Y");

                    if ("CATTLE".equals(t_Type)) {
                        budl_map.put("CRT_DT"          , paramMap.get("STDV_DT"));
                    } else  if ("PIG".equals(t_Type)) {
                        budl_map.put("CRT_DT"          , budl_map.get("processYmd"));
                    }

                    // 묶음자료 마스터성 자료                        
                    m_budl_map = (LinkedHashMap) budl_map;                        
                    
                } else {
                    // 개체 건수 증가
                    sra_cnt = sra_cnt + 1;
                    
                    if ("CATTLE".equals(t_Type)) {
                        
                        budl_map.put("SRA_HST_NO", budl_map.get("cattleNo"));
                    } else if ("PIG".equals(t_Type)) {
                        // 돼지 - 묶음번호
                        budl_map.put("SRA_HST_NO", budl_map.get("pigNo"));
                    }


                    // 개별유통식별번호 조회
                    LinkedHashMap<String, Object> resultMap_dist = new LinkedHashMap<>();
                    
                    budl_map.put("GDS_HST_NO", budl_map.get("SRA_HST_NO").toString());

                    resultMap_dist = ConnectDataHistNo((LinkedHashMap) budl_map);
                    
                    // 소인 경우 이력번호기본의 축종구분을 가져온다.
                    if ("CATTLE".equals(t_Type)) {
                        String cow_dsc = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_05", budl_map);
                        budl_map.put("SRS_DSC"    , cow_dsc  );                        

                    } else if ("PIG".equals(t_Type)) {
                        budl_map.put("SRS_DSC"    , "04"  );
                    
                    }
                    
                    
                    // 축산물 이력번호관리 기본 테이블에 Update를 한다.
                    budl_map.putAll(paramMap);
                    
                    budl_map.put("BUDL_NO"       , m_budl_map.get("BUDL_NO"));
                    budl_map.put("CRT_DT"        , m_budl_map.get("CRT_DT"));
                    budl_map.put("STR_DT"        , paramMap.get("STDV_DT"));                        
                    budl_map.put("STR_SQNO"      , paramMap.get("STDV_SQNO"));
                    
                    egovLogger.debug("### m_budl_map  =>"+budl_map);
                    
                    // 개별축산물이력번호 등록
                    resultMap = CoDataIdntNoHistInsert(paramMap, resultMap_dist);
                    
                    if (!"00".equals(resultMap.get("resultCode"))) {
                        throw infoException("국내산 - 축산물이력(묶음)번호 ["+resultMap.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                    }                    
                    
                    d_xml2JsonList.add(budl_map);
                    egovLogger.debug("######################################");
                    egovLogger.debug("### budl_map        =>"+budl_map);
                    egovLogger.debug("### d_xml2JsonList  =>"+d_xml2JsonList); 
                    egovLogger.debug("######################################");
                }
            }   //////////   for  /////////////
            // 묶음번호 정보조회 ==> 묶음번호 정보 TBL insert를 위한 List에 추가
            m_budl_map.put("IDVD_CN"         , sra_cnt);               
        
            // List 내용을 확인
            egovLogger.debug("######################################"); 
            egovLogger.debug("### m_budl_map  =>"+m_budl_map); 
            egovLogger.debug("######################################");
            egovLogger.debug("### d_xml2JsonList  =>"+d_xml2JsonList); 
            egovLogger.debug("######################################");
            
            // 묶음번호 등록               
            co2030Service.if_Co2030_insert((LinkedHashMap<String, Object>)m_budl_map, d_xml2JsonList);

            
            resultMap.clear();
            resultMap.put("resultCode", "00");
            resultMap.put("resultMsg", "SERVICE OK");
            
        } catch (IOException e) {
            e.printStackTrace();

            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "Connect Web SERVICE ERROR");
        } catch (Exception e) {
            e.printStackTrace();

            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
 
        return resultMap;    
    }

    // 국내산 - 축산물이력번호 내역 조회 On-line
    @Override
    public LinkedHashMap<String, Object> CoDataIdntNoHistInsert(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception {    
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 축산물이력번호관리기본관리  자료 등록[CoDataIdntNoHist] *********");
        egovLogger.debug("paramMap   ::: "+paramMap);
        egovLogger.debug("paramMap01 ::: "+paramMap01);
        
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();        
        LinkedHashMap<String, Object> idnt_map  = new LinkedHashMap<>();
        
        Map<String, Object> m_idnt_map = new HashMap<>(); 
        
        String infoType = "";
        String sra_grd_c = "";

        try 
        {
            List<Map<String, Object>> itemList = null; 
            itemList = (List<Map<String, Object>>) paramMap01.get("data");
            
            if ("".equals(paramMap01.get("data")) || paramMap01.get("data") != null) {
                for (Map<String, Object> t_map : itemList) {

                    // 정보타입(infoType) in '1','3','8','9' 만 정보추출한다.
                    infoType = t_map.get("infoType").toString();
                    
                    if (!"1".equals(infoType) && !"3".equals(infoType)) {
                        continue;
                    }
                    egovLogger.debug("### infoType  =>"+infoType);
                    
                    // 축종구분
                    String sra_dsc = t_map.get("traceNoType").toString();
                    String t_Type = sra_dsc.substring(0, sra_dsc.lastIndexOf("|"));                    

                    // 축산물 이력번호관리 기본 테이블에 Update를 한다.
                    t_map.putAll(paramMap);                    

                    // 축산물이력번호 정보 Map 구성
                    t_map.put("STR_DT"     , t_map.get("STDV_DT"));                        
                    t_map.put("STR_SQNO"   , t_map.get("STDV_SQNO"));
                    t_map.put("SRA_HST_NO" , t_map.get("GDS_HST_NO"));
                    t_map.put("CRT_DT"     , t_map.get("STDV_DT"));
                    t_map.put("STR_WHSE_C" , t_map.get("WHSE_C"));
                    t_map.put("STR_ID"     , t_map.get("GUSRID"));
                    t_map.put("STR_WT"     , t_map.get("STDV_QT"));

                    if ("1".equals(infoType)) {
                        if ("CATTLE".equals(t_Type)) {
                            // 축종코드
                            String srs_c = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_05", t_map);
                            egovLogger.debug("### srs_c  =>"+srs_c);
                            
                            t_map.put("SRS_DSC"    , srs_c  );
                            
                            t_map.put("SRA_SEX_NM", t_map.get("sexNm"));
                            
                            // 성별코드 가져오기
                            String sra_sex_c = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_03", t_map);
                            t_map.put("SRA_SEX_C", sra_sex_c);
                            egovLogger.debug("### sra_sex_c  =>"+sra_sex_c);
                        } else {
                            t_map.put("SRS_DSC"    , "04"  );
                        }

                        // 개체정보조회 ==> 축산물이력번호관리기본 TBL update 를 위한 List에 추가
                        m_idnt_map = (LinkedHashMap) t_map;
                    } else {
                        // 등급명
                        if ("CATTLE".equals(t_Type)) {
                            m_idnt_map.put("GRD_NM" , t_map.get("gradeNm"));
                            
                            sra_grd_c = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_02", m_idnt_map);
                            egovLogger.debug("### sra_grd_c  =>"+sra_grd_c);
                            
                            m_idnt_map.put("GRD_C"  , sra_grd_c);
                        } else if ("PIG".equals(t_Type)) {
                            m_idnt_map.put("GRD_NM" , "");
                            m_idnt_map.put("GRD_C"  , "");
                            m_idnt_map.put("SRA_HST_NO", t_map.get("pigNo"));
                            m_idnt_map.put("SRA_SEX_C" , "");
                            m_idnt_map.put("SRA_SEX_NM", "");
                        }                        

                        // openapi 에서 표준 부위코드가 없음                        
                        String std_pat_c = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_01", m_idnt_map);
                        m_idnt_map.put("STD_PAT_C", std_pat_c);
                                                             
                        // 개체정보(수입축산물정보) ==> 축산물이력정보관리기본 TBL Insert
                        m_idnt_map.put("DLR_YN"                   , "N");
                        m_idnt_map.put("IMPORT_DSC"               , "0");      // 수입구분코드
                        m_idnt_map.put("BL_NO"                    , "" );      // 선하증권번호            
                        m_idnt_map.put("KPROD_NM"                 , "" );      // 품목명                  
                        m_idnt_map.put("MAKEPLC_NM"               , "" );      // 원산지 국가명           
                        m_idnt_map.put("BUTCHFROM_DT"             , "" );      // 수출국도축시작일자      
                        m_idnt_map.put("BUTCHTO_DT"               , "" );      // 수출국도축종료일자      
                        m_idnt_map.put("BUTCH_NM"                 , "" );      // 수출국도축장명          
                        m_idnt_map.put("PRCSS_BEGIN_DT"           , "" );      // 수출국가공시작일자      
                        m_idnt_map.put("PRCSS_END_DT"             , "" );      // 수출국가공종료일자      
                        m_idnt_map.put("PRCSS_NM"                 , "" );      // 수출국가공장명          
                        m_idnt_map.put("SENDER_NM"                , "" );      // 수출업체명              
                        m_idnt_map.put("RECEIVER_NM"              , "" );      // 수입업체명              
                        m_idnt_map.put("LIMIT_TO_DT"              , "" );      // 유통기한일자            
                        m_idnt_map.put("RTRVL_TRGET_AT"           , "" );      // 회수대상여부            
                        m_idnt_map.put("DISTB_SLE_PRHIBT_AT"      , "" );      // 유통판매금지여부        
                        m_idnt_map.put("DISTB_SLE_PRHIBT_DT"      , "" );      // 유통판매금지일자        
                        m_idnt_map.put("REFRIG_CNVRS_AT"          , "" );      // 냉동전환여부            
                        m_idnt_map.put("REFRIG_DISTB_PD_BEGIN_DT" , "" );      // 냉동전환유통기한시작일자
                        m_idnt_map.put("REFRIG_DISTB_PD_END_DT"   , "" );      // 냉동전환유통기한종료일자
                        m_idnt_map.put("REGN_C"                   , "" );
                        m_idnt_map.put("REGN_NM"                  , "" );

                        // List 내용을 확인
                        egovLogger.debug("######################################"); 
                        egovLogger.debug("### m_idnt_map  =>"+m_idnt_map); 
                        egovLogger.debug("######################################");
                    }                    
                }   //////////   for  /////////////                
                
                egovLogger.debug("### 축산물이력정보관리기본 TBL Insert !!!!!");

                int Sra_cnt = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_01", m_idnt_map);
                
                if(0 < Sra_cnt) {
                    throw infoException("축산물이력번호 ["+m_idnt_map.get("SRA_HST_NO")+"]가 존재합니다.");
                } 
                egovLogger.debug("### Check select_IF_PD_SM_HST_MNG_validCheck_01 OK  =>");
                
                sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_SRS_HST_NO", m_idnt_map);
                egovLogger.debug("### insert insert_TB_CO_M_SRS_HST_NO OK  =>");
                    
                resultMap.clear();
                resultMap.put("resultCode", "00");
                resultMap.put("resultMsg", "SERVICE OK");
            }  
            
        } catch (IOException e) {
            e.printStackTrace();

            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "Connect Web SERVICE ERROR");
        } catch (Exception e) {
            e.printStackTrace();

            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
 
        return resultMap;
    }
    
    // 수입축산물이력(묶음)번호 내역 조회 Process
    @Override
    public LinkedHashMap<String, Object> CoMeatwatchHistselect(LinkedHashMap paramMap) throws Exception {
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 수입축산물이력번호관리기본관리 또는 묶음번호 자료 등록[CoMeatwatchHist] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        LinkedHashMap<String, Object> resultMap     = new LinkedHashMap<>();
        LinkedHashMap<String, Object> resultMap_01  = new LinkedHashMap<>();
        
        try 
        {
        
            int len_hst_no = paramMap.get("GDS_HST_NO").toString().length();
            
            // 수입축산물이력번호관리기본 이력번호 Validation Check
            // 묶음번호는 24자리 유통식별번호는 12자리 
            if(len_hst_no == 24) {
                // 묶음번호 조회
                paramMap.put("SUB_URL", "/selectDistbHistInfoWsrvList");
                
                resultMap_01 = ConnectCoMeatWatch(paramMap);
                
                if (!"0".equals(resultMap_01.get("returnCode"))) {
                    throw infoException("수입축산물 묶음번호 ["+resultMap_01.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                }

                resultMap = CoMeatwatchBudlNoHistselect(paramMap, resultMap_01);                
            } else {
                // 유통삭뵬번호 조회
                paramMap.put("SUB_URL", "/selectDistbHistInfoWsrvDetail");
                
                resultMap_01 = ConnectCoMeatWatch(paramMap);
                
                if (!"0".equals(resultMap_01.get("returnCode"))) {
                    throw infoException("수입축산물이력(유통식별)번호 ["+resultMap_01.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                }

                resultMap = CoMeatwatchIdntNoHistselect(paramMap, resultMap_01);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
        
        return (LinkedHashMap)resultMap;        
    }
    
    // 수입산 - 축산물이력(묶음)번호 내역 조회 (수입축산물이력관리시스템접속)
    @Override
    public LinkedHashMap<String, Object> ConnectCoMeatWatch(LinkedHashMap paramMap) throws Exception {
        // 축산물이력번호관리기본 출고항목 수정
        egovLogger.debug("************ 국내산 - 축산물이력(묶음)번호 내역 조회 (공공데이타접속)[connectDataHistNo] *********");
        egovLogger.debug("paramMap: "+paramMap.toString());
        
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        
        try 
        {
            // meatwatch 연결
            String result = "";
            StringBuilder urlBuilder = new StringBuilder(meatAnimalTraceUrl); /*URL*/
            String srsHisNo = (String) paramMap.get("GDS_HST_NO");  // 조회하고자하는 축산물이력(묶음)번호
            
            // 수입축산물이력번호관리기본  조회   
            urlBuilder.append(paramMap.get("SUB_URL"));                
            urlBuilder.append("/" + sys_id); /*sys_id*/
            urlBuilder.append("/" + URLEncoder.encode(srsHisNo, "UTF-8")); /*묶음번호*/
            urlBuilder.append("/list.do");
            egovLogger.debug("URL : " + urlBuilder.toString());
            
            URL url = new URL(urlBuilder.toString());            
            
            /* 공공데이타 OPENAPI 연결 */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            egovLogger.debug("Response code: " + conn.getResponseCode());
            
            BufferedReader rd;
            
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));                
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
    
            // Return Data 처리
            String line;
            StringBuilder sb = new StringBuilder();
            
            while ((line = rd.readLine()) != null) {
                sb.append(line);
                egovLogger.debug("### rd.line=>"+line);
            }
            rd.close();
            
            String xmlJSONObjString = sb.toString();
            egovLogger.debug("### xmlJSONObjString=>"+xmlJSONObjString);
            
            ObjectMapper objectMapper = new ObjectMapper();            
            
            // JSON 형태 데이타를 Map형태로 파싱
            // ObjectMapper().readValue(String.valueOf(jsonObjectData.get("data")), LinkedHashMap.class);
            map = objectMapper.readValue(xmlJSONObjString, LinkedHashMap.class);
            
            egovLogger.debug("### map="+map);

            
        } catch (JsonParseException e) {
            e.printStackTrace();

            map.clear();
            map.put("resultCode", "99");
            map.put("resultMsg", "Connect Web SERVICE ERROR");
        } catch (JsonMappingException e) {
            e.printStackTrace();

            map.clear();
            map.put("resultCode", "99");
            map.put("resultMsg", "Connect Web SERVICE ERROR");
        } catch (IOException e) {
            e.printStackTrace();

            map.clear();
            map.put("resultCode", "99");
            map.put("resultMsg", "Connect Web SERVICE ERROR");
        } catch (Exception e) {
            e.printStackTrace();
            
            map.clear();
            map.put("resultCode", "99");
            map.put("resultMsg", "Connect Web SERVICE ERROR");
        }
        
        return (LinkedHashMap)map;        
    }
    
    // 수입축산물묶음번호 내역 조회 On-line
    @Override
    public LinkedHashMap<String, Object> CoMeatwatchBudlNoHistselect(LinkedHashMap paramMap, LinkedHashMap paramMap01)  throws Exception {
        LinkedHashMap<String, Object> resultMap      = new LinkedHashMap<>();
        LinkedHashMap<String, Object> resultMap_dist = new LinkedHashMap<>();
        
        // 묶음번호 상세 TBL 등록을 위한 구조체 선언
        List<Map<String, Object>> d_budlList = new ArrayList<Map<String, Object>>();        
        
        try 
        {
            // 묶음번호 조회            
            Map<String, Object> params = null;
            List<Map<String, Object>> paramList = null; 
            
            params = (Map<String, Object>) paramMap01.get("bundleDetailVO");
            paramList = (List<Map<String, Object>>) paramMap01.get("bundleListVO");
 
            egovLogger.debug("######################################"); 
            egovLogger.debug("###  묶음번호 List 실행 (Parameter 확인)  ###");
            egovLogger.debug("### params="+params);
            egovLogger.debug("### paramList="+paramList);
            egovLogger.debug("######################################");
            
            int Sra_cnt = 0;

            // 묶음번호 기본 Map
            paramMap01.put("CORP_C"              , paramMap.get("CORP_C"));
            paramMap01.put("BZPL_C"              , paramMap.get("BZPL_C"));
            paramMap01.put("CRT_DT"              , params.get("bundleDe").toString().replaceAll("-", ""));
            paramMap01.put("BUDL_NO"             , params.get("bundleNo"));           // 물품이력번호
            paramMap01.put("BUDL_NO_GRD_DSC"     , "");
            paramMap01.put("BUDL_NO_GRD_OPNP_YN" , "N");
            paramMap01.put("RE_BUDL_YN"          , "N");
            paramMap01.put("RPT_DSC"             , "");
            paramMap01.put("RPT_YN"              , "Y");
            paramMap01.put("BUDL_TOT_WT"         , paramMap.get("STDV_WT"));
            paramMap01.put("DLR_TOT_WT"          , 0);
            paramMap01.put("IDVD_CN"             , Sra_cnt);
            paramMap01.put("USE_YN"              , "Y");
            paramMap01.put("GUSRID"              , paramMap.get("GUSRID"));
            
            for (Map<String, Object> t_map_1 : paramList) {
                Sra_cnt = Sra_cnt + 1;
                
                // Map 내용을 확인
                egovLogger.debug("######################################"); 
                egovLogger.debug("###  묶음번호 List Loof 실행  "+ Sra_cnt + "번째 실행  ###");
                egovLogger.debug("### t_map_1  =>"+t_map_1); 
                egovLogger.debug("######################################");

                LinkedHashMap<String, Object> d_budl_map   = new LinkedHashMap<>();
                LinkedHashMap<String, Object> resultMap_01 = new LinkedHashMap<>();
                
                // 개별유통식별번호 조회
                paramMap.put("GDS_HST_NO"       , t_map_1.get("distbIdntfcNo"));  // 묶음유통식별번호
                // 유통삭뵬번호 조회 - 이력관리기본 insert
                paramMap.put("SUB_URL", "/selectDistbHistInfoWsrvDetail");
                
                resultMap_dist = ConnectCoMeatWatch(paramMap);
                
                if (!"0".equals(resultMap_dist.get("returnCode"))) {
                    throw infoException("수입축산물이력(묶음)번호 ["+resultMap_dist.get("GDS_HST_NO")+"]가 조회중 오류가 발생했습니다.");
                }
                
                // 유통삭뵬번호 조회 - 이력관리기본 insert
                d_budl_map = CoMeatwatchIdntNoHistselect(paramMap, resultMap_dist);
                
                // 묶음번호 상세정보
                d_budl_map.put("CORP_C"              , paramMap.get("CORP_C"));
                d_budl_map.put("BZPL_C"              , paramMap.get("BZPL_C"));
                d_budl_map.put("CRT_DT"              , params.get("bundleDe").toString().replaceAll("-", ""));
                d_budl_map.put("BUDL_NO"             , params.get("bundleNo"));           // 물품이력번호
                d_budl_map.put("SRS_DSC"             , "06");                       
                d_budl_map.put("SRA_HST_NO"          , paramMap.get("GDS_HST_NO"));
                d_budl_map.put("STR_DT"              , paramMap.get("STDV_DT"));
                d_budl_map.put("STR_SQNO"            , paramMap.get("STDV_SQNO"));
                d_budl_map.put("GUSRID"              , paramMap.get("GUSRID"));
                                

                d_budlList.add(d_budl_map);
            }

            egovLogger.debug("######################################"); 
            egovLogger.debug("###  묶음번호 Insert 실행 인수확인  ###");
            egovLogger.debug("### paramMap01  ="+paramMap01);
            egovLogger.debug("### d_budlList  ="+d_budlList);
            egovLogger.debug("######################################");

            // 묶음번호 등록               
            co2030Service.if_Co2030_insert(paramMap01, d_budlList);           

//            resultMap.put("returnCode"          , "00");
//            resultMap.put("returnMsg"           , paramMap01.get("returnMsg") );
//                
//            egovLogger.debug("### resultMap="+resultMap);

            resultMap.clear();
            resultMap.put("resultCode", "00");
            resultMap.put("resultMsg", "SERVICE OK");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            
            paramMap.put("GDS_HST_NO" , paramMap01.get("BUDL_NO"));
            
            resultMap.clear();            
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
 
        return resultMap;
    }


    // 수입축산물묶음번호 내역 조회 On-line
    @Override
    public LinkedHashMap<String, Object> CoMeatwatchIdntNoHistselect(LinkedHashMap paramMap, LinkedHashMap paramMap01) throws Exception {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        
        LinkedHashMap<String, Object> hstnoMap = new LinkedHashMap<>();
        
        try 
        {
            
            egovLogger.debug("### paramMap  ===> "+paramMap);

            // 이력관리기본
            hstnoMap.put("CORP_C"                   , paramMap.get("CORP_C")                );
            hstnoMap.put("BZPL_C"                   , paramMap.get("BZPL_C")                );
            hstnoMap.put("STR_DT"                   , paramMap.get("STDV_DT")               );
            hstnoMap.put("STR_SQNO"                 , paramMap.get("STDV_SQNO")             );
            hstnoMap.put("SRA_HST_NO"               , paramMap01.get("distbIdntfcNo")       );      // 유통삭별번호
            hstnoMap.put("SRS_DSC"                  , "6"                                   );
            hstnoMap.put("STR_WHSE_C"               , paramMap.get("WHSE_C")                );
            hstnoMap.put("STR_ID"                   , paramMap.get("GUSRID")                );
            hstnoMap.put("SRA_SEX_C"                , ""                                    );
            hstnoMap.put("STD_PAT_C"                , ""                                    );
            hstnoMap.put("STR_WT"                   , paramMap.get("STDV_QT")               );
            hstnoMap.put("GRD_C"                    , ""                                    );
            hstnoMap.put("GRD_NM"                   , ""                                    );
            hstnoMap.put("DLR_YN"                   , "N"                                   );
            hstnoMap.put("DLR_DT"                   , ""                                    );
            hstnoMap.put("DLR_ID"                   , ""                                    );
            hstnoMap.put("DLR_WT"                   , 0                                     );
            hstnoMap.put("DEL_YN"                   , paramMap.get("DEL_YN")                );
            hstnoMap.put("GUSRID"                   , paramMap.get("GUSRID")                );
            // 수입육정보
            hstnoMap.put("IMPORT_DSC"               , "1"                                   );      // 수입여부구분코드
            hstnoMap.put("BL_NO"                    , paramMap01.get("blNo")                );      // 선하증권번호            
            hstnoMap.put("KPROD_NM"                 , paramMap01.get("kprodNm")             );      // 품목명                  
            hstnoMap.put("MAKEPLC_NM"               , paramMap01.get("makeplcNm")           );      // 원산지 국가명           
            hstnoMap.put("BUTCH_NM"                 , paramMap01.get("butchNm")             );      // 수출국도축장명          
            hstnoMap.put("PRCSS_NM"                 , paramMap01.get("prcssNm")             );      // 수출국가공장명          
            hstnoMap.put("SENDER_NM"                , paramMap01.get("senderNm")            );      // 수출업체명              
            hstnoMap.put("RECEIVER_NM"              , paramMap01.get("receiverNm")          );      // 수입업체명              
            hstnoMap.put("RTRVL_TRGET_AT"           , paramMap01.get("rtrvlTrgetAt")        );      // 회수대상여부            
            hstnoMap.put("DISTB_SLE_PRHIBT_AT"      , paramMap01.get("distbSlePrhibtAt")    );      // 유통판매금지여부        
            hstnoMap.put("REFRIG_CNVRS_AT"          , paramMap01.get("refrigCnvrsAt")       );      // 냉동전환여부
            
            if(paramMap01.get("butchfromDt") != null) {
                hstnoMap.put("BUTCHFROM_DT"             , paramMap01.get("butchfromDt").toString().replaceAll("-", ""));      // 수출국도축시작일자
            } else {
                hstnoMap.put("BUTCHFROM_DT"             , ""                                    );
            }
            if(paramMap01.get("butchtoDt") != null) {
                hstnoMap.put("BUTCHTO_DT"               , paramMap01.get("butchtoDt").toString().replaceAll("-", ""));      // 수출국도축종료일자      
            } else {
                hstnoMap.put("BUTCHTO_DT"               , ""                                    );
            }
            if(paramMap01.get("prcssBeginDe") != null) {
                hstnoMap.put("PRCSS_BEGIN_DT"           , paramMap01.get("prcssBeginDe").toString().replaceAll("-", ""));      // 수출국가공시작일자      
            } else {
                hstnoMap.put("PRCSS_BEGIN_DT"           , ""                                    );
            }
            if(paramMap01.get("prcssEndDe") != null) {
                hstnoMap.put("PRCSS_END_DT"             , paramMap01.get("prcssEndDe").toString().replaceAll("-", ""));      // 수출국가공종료일자      
            } else {
                hstnoMap.put("PRCSS_END_DT"             , ""                                    );
            }
            if(paramMap01.get("limitToDt") != null) {
                hstnoMap.put("LIMIT_TO_DT"              , paramMap01.get("limitToDt").toString().replaceAll("-", ""));      // 유통기한일자            
            } else {
                hstnoMap.put("LIMIT_TO_DT"              , ""                                    );
            }
            if(paramMap01.get("distbSlePrhibtDe") != null) {
                hstnoMap.put("DISTB_SLE_PRHIBT_DT"      , paramMap01.get("distbSlePrhibtDe").toString().replaceAll("-", ""));      // 유통판매금지일자        
            } else {
                hstnoMap.put("DISTB_SLE_PRHIBT_DT"      , ""                                    );
            }
            if(paramMap01.get("refrigDistbPdBeginDe") != null) {
                hstnoMap.put("REFRIG_DISTB_PD_BEGIN_DT" , paramMap01.get("refrigDistbPdBeginDe").toString().replaceAll("-", ""));      // 냉동전환유통기한시작일자
            } else {
                hstnoMap.put("REFRIG_DISTB_PD_BEGIN_DT" , ""                                    );
            }
            if(paramMap01.get("refrigDistbPdEndDe") != null) {
                hstnoMap.put("REFRIG_DISTB_PD_END_DT"   , paramMap01.get("refrigDistbPdEndDe").toString().replaceAll("-", ""));      // 냉동전환유통기한종료일자
            } else {
                hstnoMap.put("REFRIG_DISTB_PD_END_DT"   , ""                                    );
            }
            
            hstnoMap.put("REGN_C"                   , paramMap01.get("regnCode").toString().trim().replaceAll(" ", ","));
            hstnoMap.put("REGN_NM"                  , paramMap01.get("regnNm").toString().trim().replaceAll(" ", ","));

            // Map 내용을 확인
            egovLogger.debug("######################################"); 
            egovLogger.debug("### hstnoMap  =>"+hstnoMap); 
            egovLogger.debug("######################################");
            
            int CNT_1 = sqlSession.selectOne("sfmes.sqlmap.pd.select_IF_PD_SM_HST_MNG_validCheck_01", hstnoMap);
            
            if(0 < CNT_1) {
                throw infoException("축산물이력번호 ["+hstnoMap.get("SRA_HST_NO")+"]가 존재합니다.");
            } 
            egovLogger.debug("### Check select_IF_PD_SM_HST_MNG_validCheck_01 OK  =>");
            
            sqlSession.insert("sfmes.sqlmap.tb.insert_TB_CO_M_SRS_HST_NO", hstnoMap);
            
            egovLogger.debug("### 유통식별번호 - 이력관리기본TBL( TB_CO_M_SRS_HST_NO ) insert OK  =>");
            // ######################################

            resultMap.clear();
            resultMap.put("resultCode", "00");
            resultMap.put("resultMsg", "SERVICE OK");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

            resultMap.clear();
            resultMap.put("resultCode", "99");
            resultMap.put("resultMsg", "SERVICE ERROR");
        }
 
        return resultMap;
    }
}
