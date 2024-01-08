package com.sfmes.ca.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfmes.ca.service.Ca0200Service;
import com.sfmes.cm.web.CmnAbstractServiceImpl;
import com.sfmes.co.service.CommonService;

@Service("Ca0200Service")
public class Ca0200ServiceImpl extends CmnAbstractServiceImpl implements Ca0200Service {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 공통 서비스 선언
    @Resource(name = "CommonService")
    private CommonService commonService;

    
    // 채번 가져오기
    public String getRegSeqNo(LinkedHashMap paramMap, String strTableName, String strDate) throws Exception {
        //채번 서비스 호출
        String s_CORP_C  = paramMap.get("CORP_C").toString();
        String s_BZPL_C  = paramMap.get("BZPL_C").toString();
        
        String seqNo = commonService.getGvno(s_CORP_C,strTableName,s_BZPL_C, strDate, 1);
        egovLogger.debug(strTableName + " 일련번호 채번: " + seqNo);

        return seqNo;
    }
    
    /** 채무_거래처(정산처) 계약정보 체크
     * 거래처(정산처)의 정상 유무 및 외상거래,선금거래에 관련 설정된 정보를 사용하는 경우를 체크하기 위해.
     * @param  paramList
     *         CORP_C  : 회사코드
     *         ADJPL_C : 정산처코드
     *         ACG_DT  : 회계일자
     * @param  strWorkType ["1":외상매입금 / "2":기타미지급금 / "3":선수금]
     * @return void형
     * @exception Exception
     */
    protected void chkDTCntr(List<Map<String, Object>> paramList, String strWorkType) throws Exception {
        LinkedHashMap<String, Object> resultMap = null;
        
        for(Map<String, Object> map : paramList) {
            resultMap = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200TrplCtr_01", map);
            
            if ( resultMap == null ) {
                throw infoException("USERMSG:" + "정산처에 대한 정보가 없습니다. " + "전산담당자에게 문의하세요.");
            }
            
            if ( resultMap.get("USE_YN") != null && !"Y".equals(resultMap.get("USE_YN")) ) {   // 사용여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 사용불가 정산처 입니다. " + "전산담당자에게 문의하세요.");
            }
            
            if ( resultMap.get("TRPL_APL_YN") != null && !"Y".equals(resultMap.get("TRPL_APL_YN")) ) {   // 거래개시여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 거래개시가 되지 않습니다. " + "전산담당자에게 문의하세요.");
            }
            
            if ( resultMap.get("CLSD_YN") != null && "Y".equals(resultMap.get("CLSD_YN")) ) {   // 폐업여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 폐업처 입니다. " + "전산담당자에게 문의하세요.");
            }
            
            if ( resultMap.get("TR_STOP_YN") != null && "Y".equals(resultMap.get("TR_STOP_YN")) ) {   // 거래중지여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 거래중지 정산처 입니다. " + "전산담당자에게 문의하세요.");
            }
            
            if ( resultMap.get("CLO_YN") != null && "Y".equals(resultMap.get("CLO_YN")) ) {   // 해지여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 해지된 정산처 입니다. " + "전산담당자에게 문의하세요.");
            }
            
            if( resultMap.get("CTR_ST_DT") != null && resultMap.get("CTR_ED_DT") != null ) {
                if ( map.get("ACG_DT").toString().compareTo(resultMap.get("CTR_ST_DT").toString()) < 0       // 회계일자와 계약시작일자 비교 
                  || map.get("ACG_DT").toString().compareTo(resultMap.get("CTR_ED_DT").toString()) > 0 ) {   // 회계일자와 계약종료일자 비교
                    throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 정산처의 계약기간이 아닙니다. " + "전산담당자에게 문의하세요.");
                }
            }
            
            if ( resultMap.get("BY_CTR_YN") != null && !"Y".equals(resultMap.get("BY_CTR_YN")) ) {   // 매출계약여부
                throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 매출계약이 되어 있지 않습니다. " + "전산담당자에게 문의하세요.");
            }
            
            switch( strWorkType ) {
            
            case "1" :    // 외상매입금
                if ( resultMap.get("CRE_YN") != null && !"Y".equals(resultMap.get("CRE_YN")) ) {   // 외상사용여부
                    throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 외상계약이 되어 있지 않습니다. " + "전산담당자에게 문의하세요.");
                }
                
                if ( !"N".equals(map.get("REG_DSC")) ) {
                    long lCbamBac = this.getCbamBac((LinkedHashMap<String,Object>)map);
                    long lEtcUpyBac = this.getEtcUpyBac((LinkedHashMap<String,Object>)map);
                    if ( Long.parseLong(resultMap.get("CRE_LMT_AM").toString()) < lCbamBac + lEtcUpyBac + Long.parseLong(map.get("CRE_SL_AM").toString()) ) {
                        throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 외상매입금액[" + resultMap.get("CRE_LMT_AM") + "]을 초과 사용할 수 없습니다. " + "전산담당자에게 문의하세요.");
                    }
                }
                break;
                
            case "2" :    // 기타미지급금
                if ( resultMap.get("CRE_YN") != null && !"Y".equals(resultMap.get("CRE_YN")) ) {   // 외상사용여부
                    throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 외상계약이 되어 있지 않습니다. " + "전산담당자에게 문의하세요.");
                }
                
                if ( !"N".equals(map.get("REG_DSC")) ) {
                    long lCbamBac = this.getCbamBac((LinkedHashMap<String,Object>)map);
                    long lEtcUpyBac = this.getEtcUpyBac((LinkedHashMap<String,Object>)map);
                    if ( Long.parseLong(resultMap.get("CRE_LMT_AM").toString()) < lCbamBac + lEtcUpyBac + Long.parseLong(map.get("CRE_SL_AM").toString()) ) {
                        throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 외상매입금액[" + resultMap.get("CRE_LMT_AM") + "]을 초과 사용할 수 없습니다. " + "전산담당자에게 문의하세요.");
                    }
                }
                break;

            case "3" :    // 선수금
                if ( resultMap.get("PRVAM_YN") != null && !"Y".equals(resultMap.get("PRVAM_YN")) ) {   // 선급금계약여부
                    throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 선급금계약이 되어 있지 않습니다. " + "전산담당자에게 문의하세요.");
                }
                
                // 입금(등록)일 경우만.
                if ( "N".equals(map.get("REG_DSC")) ) {
                    long lPrvBac = this.getPrvBac((LinkedHashMap<String,Object>)map);
                    if ( Long.parseLong(resultMap.get("PRVAM_LMT_AM").toString()) < lPrvBac + Long.parseLong(map.get("PRV_AM").toString()) ) {
                        throw infoException("USERMSG:[" + resultMap.get("TRPL_NM") + "] 선수금한도금액[" + resultMap.get("PRVAM_LMT_AM") + "]을 초과 입금할 수 없습니다. " + "전산담당자에게 문의하세요.");
                    }
                }
                break;
            }
        }
    }

    
    /* =================================================================================== */
    /* ============================= 외상매입금 ============================================= */
    
    // 외상매입금내역 저장
    protected void procCbam(LinkedHashMap paramMap1, LinkedHashMap paramMap2) throws Exception {
        
        egovLogger.debug("************ 외상매입금 등록(정정/삭제)[Ca0200 - procCbam] *********");
        egovLogger.debug("paramMap1: "  + paramMap1.toString());
        egovLogger.debug("paramMap2: "  + paramMap2.toString());
        
        int iEffectRow = 0;
        
        // ----------------------------------------
        // 채무_외상매입금기본 처리
        // ----------------------------------------
        
        // 등록/정정(삭제) 확인
        if( "N".equals( paramMap1.get("SLP_NML_YN").toString() ) ) {
            
            // 1-1) 삭제인 경우(전표정상여부 "N" 으로)
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_CBAM_02", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_CBAM_02][paramMap1] update row : "  + iEffectRow);
        }
        else if( "0".equals( paramMap1.get("REG_SQNO").toString() ) || paramMap1.get("REG_SQNO") == null ) {
            
            // 1-2) 등록인 경우
            // 채번 가져오기
            String strTableName = new String("TB_DT_M_CBAM");
            String seqNo = this.getRegSeqNo(paramMap1, strTableName, paramMap1.get("REG_DT").toString());
            paramMap1.put("REG_SQNO", seqNo);

            // 등록처리.
            iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_CBAM", paramMap1);
            egovLogger.debug("[insert_TB_DT_M_CBAM][paramMap1] insert row : "  + iEffectRow);
        }
        else {
            
            // 1-3) 정정(취소)인 경우.
            //paramMap1.put("DEL_YN", "Y");
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_CBAM_01", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_CBAM_01][paramMap1] update row : "  + iEffectRow);
            
            if( !paramMap2.isEmpty() ) {
                
                // 1-4) 정정으로 인한 신규등록 경우
                // 채번 가져오기
                String strTableName = new String("TB_DT_M_CBAM");
                String seqNo = this.getRegSeqNo(paramMap2, strTableName, paramMap1.get("REG_DT").toString());
                paramMap2.put("REG_SQNO", seqNo);

                // 정정내용 등록 처리.
                //paramMap2.put("DEL_YN", "N");
                iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_CBAM", paramMap2);
                egovLogger.debug("[insert_TB_DT_M_CBAM][paramMap2] insert row : "  + iEffectRow);
            }
        }
        
        // ----------------------------------------
        // 채무_외상매입금월별집계 처리
        // ----------------------------------------
        /* 월별집계 건별 월집계 반영시 */
        iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_CBAM_MON_01", paramMap1);
        egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_CBAM_MON_01][paramMap1] merge row : "  + iEffectRow);
        
        
        if( !paramMap2.isEmpty() ) {
            iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_CBAM_MON_01", paramMap2);
            egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_CBAM_MON_01][paramMap2] merge row : "  + iEffectRow);
        }
        
    }
    
    /**
     * 외상매입금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 등록)
     *        === 지급등록 발생의 경우 입력정보 === [REG_DSC:N]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매입금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 외상매입금  
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 지급등록 지급의 경우 입력정보 === [REG_DSC:P]
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (외상매입금 지급일 경우 'P'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 외상매입금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT10:외상매입금지급등록 [미등록시:DT10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금내역 저장 (다건처리)
    @Override
    public void Call_saveCbam(List<Map<String, Object>> paramList) throws Exception {
        egovLogger.debug("************ 외상매입금 리스트 등록(정정/삭제)[Call_saveCbam] *********");
        egovLogger.debug("paramList: "  + paramList.toString());

        LinkedHashMap<String, Object> resultMap = null;
        String resultMsg = null;
        
        // 입력리스트 확인.
        if( paramList == null || paramList.size() == 0 ) {
            throw infoException("USERMSG:외상매입금 기본 처리내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 회사코드 null 여부 체크.
        if( paramList.get(0).get("CORP_C") == null ) {
            throw infoException("USERMSG:외상매입금 회사코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 사업장코드 null 여부 체크.
        if( paramList.get(0).get("BZPL_C") == null ) {
            throw infoException("USERMSG:외상매입금 사업장코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 정산처코드 null 여부 체크.
        if( paramList.get(0).get("ADJPL_C") == null ) {
            throw infoException("USERMSG:외상매입금 정산처코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 외상매입금액 null 여부 체크.
        if( paramList.get(0).get("CRE_BY_AM") == null ) {
            throw infoException("USERMSG:외상매입금액 정보가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 등록구분코드 null 여부 체크.
        if( paramList.get(0).get("REG_DSC") == null ) {
            throw infoException("USERMSG:외상매입금 등록구분코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        for(Map<String, Object> map : paramList) {
            
            // Input Validation.
            if( "N".equals(paramList.get(0).get("REG_DSC")) ) {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200CBAMValidDet01", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200CBAMValidDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
            else {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200CBAMValidDet02", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200CBAMValidDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
        }
        
        /* 차후 체크여부에 따라 주석 부분을 배제하여 사용. - kdkim
        // 채무_거래처(정산처) 계약정보 체크
        this.chkDTCntr(paramList, new String("1"));
        //*/

        for(Map<String, Object> map : paramList) {
            
            LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap<String, Object>();
            LinkedHashMap<String, Object> paramMap2 = new LinkedHashMap<String, Object>();
            
            resultMap = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200CBAM_01", map);
            
            if( resultMap == null ) {    // 등록 처리

                // for debug.
                egovLogger.debug("등록 : " + map.toString());

                // 기준잔액 넘는지 확인. (지급일 경우만 체크한다. / 발생의 경우는 제한을 두지 않는다.)
                if( !"N".equals(map.get("REG_DSC")) ) {
                    resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200CBAMPayLmtDet01", map);
                    if( resultMsg == null ) {
                        egovLogger.debug("[ERROR][selectCa0200CBAMPayLmtDet01]" + map.toString() + " [resultMsg] 등록된 외상매입금 정보가 존재하지 않습니다.");
                        throw infoException("USERMSG: 등록된 외상매입금 정보가 존재하지 않습니다.");
                    }
                    else if  (!resultMsg.equals("OK")) {
                        egovLogger.debug("[ERROR][selectCa0200CBAMPayLmtDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                        throw infoException("USERMSG:" + resultMsg);
                    }                    
                }
                
                // 등록 정보
                paramMap1.putAll( (LinkedHashMap<String, Object>) map );
                paramMap1.put("REG_DT", strToday);
                paramMap1.put("REG_SQNO", 0);
                paramMap1.put("DEL_YN", "N");
                paramMap1.put("TR_SQNO", map.get("TR_SQNO"));
                paramMap1.put("GUSRID", map.get("GUSRID"));
                if( paramMap1.get("TR_BSN_DSC") == null || "".equals(paramMap1.get("TR_BSN_DSC")) ) {
                    if( "N".equals(map.get("REG_DSC")) ) {
                        paramMap1.put("TR_BSN_DSC", "BY10");    // 거래업무구분코드 [BY10:매입등록]
                    }
                    else {
                        paramMap1.put("TR_BSN_DSC", "DT10");    // 거래업무구분코드 [DT10:외상매입금지급등록]
                    }
                }
                
                // BY11:매입반출등록 이면
                if( "N".equals(paramMap1.get("REG_DSC")) && "BY11".equals(paramMap1.get("TR_BSN_DSC")) ) {
                    paramMap1.put("CRE_BY_AM", Long.parseLong(paramMap1.get("CRE_BY_AM").toString()) * -1);
                }
            }
            else {
                
                // for debug.
                egovLogger.debug("정정/삭제 : " + map.toString());
                egovLogger.debug("resultMap : " + resultMap.toString());
                
                if( "N".equals( map.get("SLP_NML_YN").toString() ) ) {    // 삭제 처리 (전표정상여부가 'N'인 경우)

                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("SLP_NML_YN", "N");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else if( "0".equals( map.get("CRE_BY_AM").toString() ) ) {    // 취소 처리

                    // 취소정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else {      // 정정 처리
                    
                    // 기준잔액 넘는지 확인. (지급일 경우만 체크한다. / 발생의 경우는 제한을 두지 않는다.)
                    if( !"N".equals(map.get("REG_DSC")) ) {
                        resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200CBAMPayLmtDet02", map);
                        if( resultMsg == null ) {
                            egovLogger.debug("[ERROR][selectCa0200CBAMPayLmtDet02]" + map.toString() + " [resultMsg] 외상매입금 월집계정보가 존재하지 않습니다.");
                            throw infoException("USERMSG: 외상매입금 월집계정보가 존재하지 않습니다.");
                        }
                        else if  (!resultMsg.equals("OK")) {
                            egovLogger.debug("[ERROR][selectCa0200CBAMPayLmtDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                            throw infoException("USERMSG:" + resultMsg);
                        }                        
                    }
                    
                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                    
                    // 등록정보
                    paramMap2.putAll(resultMap);
                    paramMap2.put("REG_SQNO", 0);
                    paramMap2.put("CRE_BY_AM", map.get("CRE_BY_AM"));
                    paramMap2.put("DEL_YN", "N");
                    paramMap2.put("RMK_CNTN", map.get("RMK_CNTN"));
                    paramMap2.put("GUSRID", map.get("GUSRID"));
                    if( !"N".equals(map.get("REG_DSC")) ) {
                        paramMap2.put("PY_STL_DSC"  , map.get("PY_STL_DSC")  );
                        
                        // 지급방법이 계좌번호이면.
                        if( "Y".equals(sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200AcntTranYN01", map))) {
                            paramMap2.put("STL_ACNO"  , map.get("STL_ACNO")  );
                            paramMap2.put("BNK_C"     , map.get("BNK_C")     );
                            paramMap2.put("DPR_NM"    , map.get("DPR_NM")    );
                        }
                    }
                    
                    // BY11:매입반출등록 이면
                    if( "N".equals(map.get("REG_DSC")) && "BY11".equals(map.get("TR_BSN_DSC")) ) {
                        paramMap2.put("CRE_BY_AM", Long.parseLong(paramMap2.get("CRE_BY_AM").toString()) * -1);
                    }
                }
            }
            
            // for debug.
            //System.out.println("paramMap1 : " + paramMap1);
            //System.out.println("paramMap2 : " + paramMap2);
            
            this.procCbam(paramMap1, paramMap2);
        }
    }
    
    /**
     * 외상매입금 (발생)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 [발생]등록)
     *        === 지급등록 발생 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 외상매입금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금 등록[발생] 저장
    public void Call_saveCbam_Ocr( List<Map<String, Object>> paramList ) throws Exception {
        for(Map<String, Object> map : paramList) {
            map.put("REG_DSC", "N");
        }
        
        this.Call_saveCbam( paramList );
    }
    
    /**
     * 외상매입금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_외상매입금기본 지급[등록])
     *        === 지급등록 지급 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 외상매입금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT10:외상매입금지급등록 [미등록시:DT10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 외상매입금 지급 저장
    public void Call_saveCbam_Pay( List<Map<String, Object>> paramList ) throws Exception {
        for(Map<String, Object> map : paramList) {
            map.put("REG_DSC", "P");
        }
        
        this.Call_saveCbam( paramList );
    }

    
    /* =================================================================================== */
    /* ============================= 기타미지급금 ============================================ */
    
    // 기타미지급금내역 저장
    protected void procEtcUpy(LinkedHashMap paramMap1, LinkedHashMap paramMap2) throws Exception {
        
        egovLogger.debug("************ 기타미지급금 등록(정정/삭제)[Ca0200 - procEtcUpy] *********");
        egovLogger.debug("paramMap1: "  + paramMap1.toString());
        egovLogger.debug("paramMap2: "  + paramMap2.toString());
        
        int iEffectRow = 0;
        
        // ----------------------------------------
        // 채무_기타미지급금기본 처리
        // ----------------------------------------
        
        // 등록/정정(삭제) 확인
        if( "N".equals( paramMap1.get("SLP_NML_YN").toString() ) ) {
            
            // 1-1) 삭제인 경우(전표정상여부 "N" 으로)
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_ETC_UPY_02", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_ETC_UPY_02][paramMap1] update row : "  + iEffectRow);
        }
        else if( "0".equals( paramMap1.get("REG_SQNO").toString() ) || paramMap1.get("REG_SQNO") == null ) {
            
            // 1-2) 등록인 경우
            // 채번 가져오기
            String strTableName = new String("TB_DT_M_ETC_UPY");
            String seqNo = this.getRegSeqNo(paramMap1, strTableName, paramMap1.get("REG_DT").toString());
            paramMap1.put("REG_SQNO", seqNo);

            // 등록처리.
            iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_ETC_UPY", paramMap1);
            egovLogger.debug("[insert_TB_DT_M_ETC_UPY][paramMap1] insert row : "  + iEffectRow);
        }
        else {
            
            // 1-3) 정정(취소)인 경우.
            //paramMap1.put("DEL_YN", "Y");
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_ETC_UPY_01", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_ETC_UPY_01][paramMap1] update row : "  + iEffectRow);
            
            if( !paramMap2.isEmpty() ) {
                
                // 1-4) 정정으로 인한 신규등록 경우
                // 채번 가져오기
                String strTableName = new String("TB_DT_M_ETC_UPY");
                String seqNo = this.getRegSeqNo(paramMap2, strTableName, paramMap1.get("REG_DT").toString());
                paramMap2.put("REG_SQNO", seqNo);

                // 정정내용 등록 처리.
                //paramMap2.put("DEL_YN", "N");
                iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_ETC_UPY", paramMap2);
                egovLogger.debug("[insert_TB_DT_M_ETC_UPY][paramMap2] insert row : "  + iEffectRow);
            }
        }
        
        // ----------------------------------------
        // 채무_기타미지급금월별집계 처리
        // ----------------------------------------
        /* 월별집계 건별 월집계 반영시 */
        iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_ETC_UPY_MON_01", paramMap1);
        egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_ETC_UPY_MON_01][paramMap1] merge row : "  + iEffectRow);
        
        
        if( !paramMap2.isEmpty() ) {
            iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_ETC_UPY_MON_01", paramMap2);
            egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_ETC_UPY_MON_01][paramMap2] merge row : "  + iEffectRow);
        }
        
    }
    
    /**
     * 기타미지급금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 등록)
     *        === 지급등록 발생의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (기타미지급금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 기타미지급금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 지급등록 지급의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (기타미지급금 지급일 경우 'P'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생] / ['P':지급])
     *         CRE_BY_AM  : 기타미지급금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT30:기타미지급금지급등록 [미등록시:DT30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금내역 저장(다건)
    public void Call_saveEtcUpy( List<Map<String, Object>> paramList ) throws Exception {
        egovLogger.debug("************ 기타미지급금 리스트 등록(정정/삭제)[Call_saveEtcUpy] *********");
        egovLogger.debug("paramList: "  + paramList.toString());

        LinkedHashMap<String, Object> resultMap = null;
        String resultMsg = null;
        
        // 입력리스트 확인.
        if( paramList == null || paramList.size() == 0 ) {
            throw infoException("USERMSG:기타미지급금 기본 처리내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 회사코드 null 여부 체크.
        if( paramList.get(0).get("CORP_C") == null ) {
            throw infoException("USERMSG:기타미지급금 회사코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 사업장코드 null 여부 체크.
        if( paramList.get(0).get("BZPL_C") == null ) {
            throw infoException("USERMSG:기타미지급금 사업장코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 정산처코드 null 여부 체크.
        if( paramList.get(0).get("ADJPL_C") == null ) {
            throw infoException("USERMSG:기타미지급금 정산처코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 기타미지급금 null 여부 체크.
        if( paramList.get(0).get("CRE_BY_AM") == null ) {
            throw infoException("USERMSG:기타미지급금액 정보가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 등록구분코드 null 여부 체크.
        if( paramList.get(0).get("REG_DSC") == null ) {
            throw infoException("USERMSG:기타미지급금 등록구분코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        for(Map<String, Object> map : paramList) {
            
            // Input Validation.
            if( "N".equals(paramList.get(0).get("REG_DSC")) ) {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200EtcUpyValidDet01", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200EtcUpyValidDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
            else {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200EtcUpyValidDet02", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200EtcUpyValidDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
        }
        
        /* 차후 체크여부에 따라 주석 부분을 배제하여 사용. - kdkim
        // 채무_거래처(정산처) 계약정보 체크
        this.chkDTCntr(paramList, new String("2"));
        //*/
            
        for(Map<String, Object> map : paramList) {
            
            LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap<String, Object>();
            LinkedHashMap<String, Object> paramMap2 = new LinkedHashMap<String, Object>();
            
            resultMap = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200EtcUpy_01", map);
            
            if( resultMap == null ) {    // 등록 처리

                // for debug.
                egovLogger.debug("등록 : " + map.toString());

                // 기준잔액 넘는지 확인. (지급일 경우만 체크한다. / 발생의 경우는 제한을 두지 않는다.)
                if( !"N".equals(map.get("REG_DSC")) ) {
                    resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200EtcUpyPayLmtDet01", map);
                    if( resultMsg == null ) {
                        egovLogger.debug("[ERROR][selectCa0200EtcUpyPayLmtDet01]" + map.toString() + " [resultMsg] 등록된 기타미지급금 정보가 존재하지 않습니다.");
                        throw infoException("USERMSG: 등록된 기타미지급금 정보가 존재하지 않습니다.");
                    }
                    else if  (!resultMsg.equals("OK")) {
                        egovLogger.debug("[ERROR][selectCa0200EtcUpyPayLmtDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                        throw infoException("USERMSG:" + resultMsg);
                    }                    
                }
                
                // 등록 정보
                paramMap1.putAll( (LinkedHashMap<String, Object>) map );
                paramMap1.put("REG_DT", strToday);
                paramMap1.put("REG_SQNO", 0);
                paramMap1.put("DEL_YN", "N");
                paramMap1.put("TR_SQNO", map.get("TR_SQNO"));
                paramMap1.put("GUSRID", map.get("GUSRID"));
                if( paramMap1.get("TR_BSN_DSC") == null || "".equals(paramMap1.get("TR_BSN_DSC")) ) {
                    if( "N".equals(map.get("REG_DSC")) ) {
                        paramMap1.put("TR_BSN_DSC", "BY10");    // 거래업무구분코드 [BY10:매입등록]
                    }
                    else {
                        paramMap1.put("TR_BSN_DSC", "DT30");    // 거래업무구분코드 [DT30:기타미지급금지급등록]
                    }
                }
                
                // BY11:미지급반출등록 이면
                if( "N".equals(paramMap1.get("REG_DSC")) && "BY11".equals(paramMap1.get("TR_BSN_DSC")) ) {
                    paramMap1.put("CRE_BY_AM", Long.parseLong(paramMap1.get("CRE_BY_AM").toString()) * -1);
                }
            }
            else {
                
                // for debug.
                egovLogger.debug("정정/삭제 : " + map.toString());
                egovLogger.debug("resultMap : " + resultMap.toString());
                
                if( "N".equals( map.get("SLP_NML_YN").toString() ) ) {    // 삭제 처리 (전표정상여부가 'N'인 경우)

                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("SLP_NML_YN", "N");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else if( "0".equals( map.get("CRE_BY_AM").toString() ) ) {    // 취소 처리

                    // 취소정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else {      // 정정 처리
                    
                    // 기준잔액 넘는지 확인. (지급일 경우만 체크한다. / 발생의 경우는 제한을 두지 않는다.)
                    if( !"N".equals(map.get("REG_DSC")) ) {
                        resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200EtcUpyPayLmtDet02", map);
                        if( resultMsg == null ) {
                            egovLogger.debug("[ERROR][selectCa0200EtcUpyPayLmtDet02]" + map.toString() + " [resultMsg] 기타미지급금 월집계정보가 존재하지 않습니다.");
                            throw infoException("USERMSG: 기타미지급금 월집계정보가 존재하지 않습니다.");
                        }
                        else if  (!resultMsg.equals("OK")) {
                            egovLogger.debug("[ERROR][selectCa0200EtcUpyPayLmtDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                            throw infoException("USERMSG:" + resultMsg);
                        }                        
                    }
                    
                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                    
                    // 등록정보
                    paramMap2.putAll(resultMap);
                    paramMap2.put("REG_SQNO", 0);
                    paramMap2.put("CRE_BY_AM", map.get("CRE_BY_AM"));
                    paramMap2.put("DEL_YN", "N");
                    paramMap2.put("RMK_CNTN", map.get("RMK_CNTN"));
                    paramMap2.put("GUSRID", map.get("GUSRID"));
                    if( !"N".equals(map.get("REG_DSC")) ) {
                        paramMap2.put("PY_STL_DSC"  , map.get("PY_STL_DSC")  );
                        
                        // 지급방법이 계좌번호이면.
                        if( "Y".equals(sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200AcntTranYN01", map))) {
                            paramMap2.put("STL_ACNO"  , map.get("STL_ACNO")  );
                            paramMap2.put("BNK_C"     , map.get("BNK_C")     );
                            paramMap2.put("DPR_NM"    , map.get("DPR_NM")    );
                        }
                    }
                    
                    // BY11:미지급반출등록 이면
                    if( "N".equals(map.get("REG_DSC")) && "BY11".equals(map.get("TR_BSN_DSC")) ) {
                        paramMap2.put("CRE_BY_AM", Long.parseLong(paramMap2.get("CRE_BY_AM").toString()) * -1);
                    }
                }
            }
            
            // for debug.
            //System.out.println("paramMap1 : " + paramMap1);
            //System.out.println("paramMap2 : " + paramMap2);
            
            this.procEtcUpy(paramMap1, paramMap2);
        }
    }
    
    /**
     * 기타미지급금 (발생)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 [발생]등록)
     *        === 지급등록 발생 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         PY_PLA_DT  : 지급예정일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 기타미지급금 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (BY10:매입등록 [미등록시:BY10으로 세팅함.] / BY11:매입반출등록)
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금 등록[발생] 저장
    public void Call_saveEtcUpy_Ocr( List<Map<String, Object>> paramList ) throws Exception {
        for( Map<String, Object> map : paramList ) {
            map.put("REG_DSC", "N");
        }
        
        this.Call_saveEtcUpy(paramList);
    }
    
    /**
     * 기타미지급금 (지급)등록[/정정/삭제] 한다.
     * @param paramList(채무_기타미지급금기본 [지급]등록)
     *        === 지급등록 지급 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         CRE_BY_AM  : 기타미지급금 
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT30:기타미지급금지급등록 [미등록시:DT30으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 기타미지급금 지급 저장
    public void Call_saveEtcUpy_Pay( List<Map<String, Object>> paramList ) throws Exception {
        for( Map<String, Object> map : paramList ) {
            map.put("REG_DSC", "P");
        }
        
        this.Call_saveEtcUpy(paramList);
    }
    
    /* =================================================================================== */
    /* =============================== 선수금 ============================================== */
    // 선수금내역 저장
    protected void procPrv(LinkedHashMap paramMap1, LinkedHashMap paramMap2) throws Exception {

        egovLogger.debug("************ 선수금 등록(정정/삭제)[Ca0200 - procPrv] *********");
        egovLogger.debug("paramMap1: "  + paramMap1.toString());
        egovLogger.debug("paramMap2: "  + paramMap2.toString());
        
        int iEffectRow = 0;
        
        // ----------------------------------------
        // 채권_선수금기본 처리
        // ----------------------------------------
        
        // 등록/정정(삭제) 확인
        if( "N".equals( paramMap1.get("SLP_NML_YN").toString() ) ) {
            
            // 1-1) 삭제인 경우(전표정상여부 "N" 으로)
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_PRV_02", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_PRV_02][paramMap1] update row : "  + iEffectRow);
        }
        else if( "0".equals( paramMap1.get("REG_SQNO").toString() ) || paramMap1.get("REG_SQNO") == null ) {
            
            // 1-2) 등록인 경우
            // 채번 가져오기
            String strTableName = new String("TB_DT_M_PRV");
            String seqNo = this.getRegSeqNo(paramMap1, strTableName, paramMap1.get("REG_DT").toString());
            paramMap1.put("REG_SQNO", seqNo);

            // 등록처리.
            iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_PRV", paramMap1);
            egovLogger.debug("[insert_TB_DT_M_PRV][paramMap1] insert row : "  + iEffectRow);
        }
        else {
            
            // 1-3) 정정(취소)인 경우.
            //paramMap1.put("DEL_YN", "Y");
            iEffectRow = sqlSession.update("sfmes.sqlmap.ca.update_DEL_YN_FOR_TB_DT_M_PRV_01", paramMap1);
            egovLogger.debug("[update_DEL_YN_FOR_TB_DT_M_PRV_01][paramMap1] update row : "  + iEffectRow);
            
            if( !paramMap2.isEmpty() ) {
                
                // 1-4) 정정으로 인한 신규등록 경우
                // 채번 가져오기
                String strTableName = new String("TB_DT_M_PRV");
                String seqNo = this.getRegSeqNo(paramMap2, strTableName, paramMap1.get("REG_DT").toString());
                paramMap2.put("REG_SQNO", seqNo);

                // 정정내용 등록 처리.
                //paramMap2.put("DEL_YN", "N");
                iEffectRow = sqlSession.insert("sfmes.sqlmap.tb.insert_TB_DT_M_PRV", paramMap2);
                egovLogger.debug("[insert_TB_DT_M_PRV][paramMap2] insert row : "  + iEffectRow);
            }
        }
        
        // ----------------------------------------
        // 채권_선수금월별집계 처리
        // ----------------------------------------
        /* 월별집계 건별 월집계 반영시 */
        iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_PRV_MON_01", paramMap1);
        egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_PRV_MON_01][paramMap1] merge row : "  + iEffectRow);
        
        
        if( !paramMap2.isEmpty() ) {
            iEffectRow = sqlSession.insert("sfmes.sqlmap.ca.merge_RealTime_FOR_TB_DT_S_PRV_MON_01", paramMap2);
            egovLogger.debug("[merge_RealTime_FOR_TB_DT_S_PRV_MON_01][paramMap2] merge row : "  + iEffectRow);
        }
        
    }
    
    /**
     * 선수금 (입금)등록[/정정/삭제] 한다.
     * @param paramList(채무_선수금기본 등록)
     *        === 선수금등록 발생(입금)의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (선수금 등록일 경우 'N'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|입금] / ['U':사용])
     *         PRV_AM     : 선수금액 
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     *         
     *        === 선수금등록 사용의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         REG_DSC    : 등록구분코드 (선수금 등록일 경우 'U'으로 세팅되어야 내부에서 구분할 수 있음 | ['N':발생|입금] / ['U':사용])
     *         PRV_AM     : 선수금액
     *         PY_STL_DSC : 지급결제구분코드 
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 입금/사용 등록
    public void Call_savePrv( List<Map<String, Object>> paramList ) throws Exception {
        egovLogger.debug("************ 선수금 입금/사용 등록(정정/삭제)[Call_savePrv] *********");
        egovLogger.debug("paramList: "  + paramList.toString());

        LinkedHashMap<String, Object> resultMap = null;
        String resultMsg = null;
        
        // 입력리스트 확인.
        if( paramList == null || paramList.size() == 0 ) {
            throw infoException("USERMSG:선수금 기본 처리내역이 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 회사코드 null 여부 체크.
        if( paramList.get(0).get("CORP_C") == null ) {
            throw infoException("USERMSG:선수금 회사코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 사업장코드 null 여부 체크.
        if( paramList.get(0).get("BZPL_C") == null ) {
            throw infoException("USERMSG:선수금 사업장코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 정산처코드 null 여부 체크.
        if( paramList.get(0).get("ADJPL_C") == null ) {
            throw infoException("USERMSG:선수금 정산처코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 선수금 null 여부 체크.
        if( paramList.get(0).get("PRV_AM") == null ) {
            throw infoException("USERMSG:선수금액 정보가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        // 등록구분코드 null 여부 체크.
        if( paramList.get(0).get("REG_DSC") == null ) {
            throw infoException("USERMSG:선급금 등록구분코드가 없습니다. 전산담당자에게 문의하세요.");
        }
        
        //오늘날짜구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        
        for(Map<String, Object> map : paramList) {
            // Input Validation.
            if( "N".equals(paramList.get(0).get("REG_DSC")) ) {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvValidDet01", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200PrvValidDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
            else {
                resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvValidDet02", map);
                if  (!resultMsg.equals("OK")) {
                    egovLogger.debug("[ERROR][selectCa0200PrvValidDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                    throw infoException("USERMSG:" + resultMsg + "전산담당자에게 문의하세요");
                }
            }
        }
        
        /* 차후 체크여부에 따라 주석 부분을 배제하여 사용. - kdkim
        // 채무_거래처(정산처) 계약정보 체크
        this.chkDTCntr(paramList, new String("3"));
        //*/
            
        for(Map<String, Object> map : paramList) {
            
            LinkedHashMap<String, Object> paramMap1 = new LinkedHashMap<String, Object>();
            LinkedHashMap<String, Object> paramMap2 = new LinkedHashMap<String, Object>();
            
            resultMap = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200Prv_01", map);
            
            if( resultMap == null ) {    // 등록 처리

                // for debug.
                egovLogger.debug("등록 : " + map.toString());

                // 기준잔액 넘는지 확인. (사용일 경우만 체크한다. / 발생[입금]의 경우는 제한을 두지 않는다.)
                if( !"N".equals(map.get("REG_DSC")) ) {
                    resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvUseLmtDet01", map);
                    if ( resultMsg == null ) {
                        egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet01]" + map.toString() + " [resultMsg]" + " 등록된 선수금 정보가 없습니다.");
                        throw infoException("USERMSG:" + " 등록된 선수금 정보가 없습니다.");
                    }
                    else if  (!resultMsg.equals("OK")) {
                        egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet01]" + map.toString() + " [resultMsg]" + resultMsg);
                        throw infoException("USERMSG:" + resultMsg);
                    }                
                }
                
                // 등록 정보
                paramMap1.putAll( (LinkedHashMap<String, Object>) map );
                paramMap1.put("REG_DT", strToday);
                paramMap1.put("REG_SQNO", 0);
                paramMap1.put("DEL_YN", "N");
                paramMap1.put("TR_SQNO", map.get("TR_SQNO"));
                paramMap1.put("GUSRID", map.get("GUSRID"));
                if( paramMap1.get("TR_BSN_DSC") == null || "".equals(paramMap1.get("TR_BSN_DSC")) ) {
                    if( "N".equals(map.get("REG_DSC")) ) {
                        paramMap1.put("TR_BSN_DSC", "DT20");    // 거래업무구분코드 [DT20:선수금입금등록]
                    }
                    else {
                        paramMap1.put("TR_BSN_DSC", "DT21");    // 거래업무구분코드 [DT21:선수금사용등록]
                    }
                }
            }
            else {
                
                // for debug.
                egovLogger.debug("정정/삭제 : " + map.toString());
                egovLogger.debug("resultMap : " + resultMap.toString());
                
                if( "N".equals( map.get("SLP_NML_YN").toString() ) ) {    // 삭제 처리 (전표정상여부가 'N'인 경우)

                    // 기준잔액 넘는지 확인. (입금등록만 체크한다.)
                    if( "N".equals(map.get("REG_DSC")) ) {
                        resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvUseLmtDet03", resultMap);
                        if( resultMsg == null ) {
                            egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet03]" + map.toString() + " [resultMsg] 선수금 월집계정보가 존재하지 않습니다.");
                            throw infoException("USERMSG: 선수금 월집계정보가 존재하지 않습니다.");
                        }
                        else if  (!resultMsg.equals("OK")) {
                            egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet03]" + map.toString() + " [resultMsg]" + resultMsg);
                            throw infoException("USERMSG:" + resultMsg);
                        }
                    }
                    
                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("SLP_NML_YN", "N");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else if( "0".equals( map.get("PRV_AM").toString() ) ) {    // 취소 처리

                    // 기준잔액 넘는지 확인. (입금등록만 체크한다.)
                    if( "N".equals(map.get("REG_DSC")) ) {
                        resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvUseLmtDet03", map);
                        if( resultMsg == null ) {
                            egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet03]" + map.toString() + " [resultMsg] 선수금 월집계정보가 존재하지 않습니다.");
                            throw infoException("USERMSG: 선수금 월집계정보가 존재하지 않습니다.");
                        }
                        else if  (!resultMsg.equals("OK")) {
                            egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet03]" + map.toString() + " [resultMsg]" + resultMsg);
                            throw infoException("USERMSG:" + resultMsg);
                        }
                    }

                    // 취소정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                }
                else {      // 정정 처리
                    
                    // 기준잔액 넘는지 확인.
                    resultMsg = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200PrvUseLmtDet02", map);
                    if( resultMsg == null ) {
                        egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet02]" + map.toString() + " [resultMsg] 선수금 월집계정보가 존재하지 않습니다.");
                        throw infoException("USERMSG: 선수금 월집계정보가 존재하지 않습니다.");
                    }
                    else if  (!resultMsg.equals("OK")) {
                        egovLogger.debug("[ERROR][selectCa0200PrvUseLmtDet02]" + map.toString() + " [resultMsg]" + resultMsg);
                        throw infoException("USERMSG:" + resultMsg);
                    }
                    
                    // 삭제정보
                    paramMap1.putAll(resultMap);
                    paramMap1.put("DEL_YN", "Y");
                    paramMap1.put("GUSRID", map.get("GUSRID"));
                    
                    // 등록정보
                    paramMap2.putAll(resultMap);
                    paramMap2.put("REG_SQNO", 0);
                    paramMap2.put("PRV_AM", map.get("PRV_AM"));
                    paramMap2.put("DEL_YN", "N");
                    paramMap2.put("RMK_CNTN", map.get("RMK_CNTN"));
                    paramMap2.put("GUSRID", map.get("GUSRID"));
                    if( !"N".equals(map.get("REG_DSC")) ) {
                        paramMap2.put("PY_STL_DSC"  , map.get("PY_STL_DSC")  );
                        
                        // 지급방법이 계좌번호이면.
                        if( "Y".equals(sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200AcntTranYN01", map))) {
                            paramMap2.put("STL_ACNO"  , map.get("STL_ACNO")  );
                            paramMap2.put("BNK_C"     , map.get("BNK_C")     );
                            paramMap2.put("DPR_NM"    , map.get("DPR_NM")    );
                        }
                    }
                }
            }
            
            // for debug.
            //System.out.println("paramMap1 : " + paramMap1);
            //System.out.println("paramMap2 : " + paramMap2);
            
            this.procPrv(paramMap1, paramMap2);
        }
    }
    
    /**
     * 선수금 (입금)등록[/정정/삭제] 한다.
     * @param paramList(채무_선수금기본 등록)
     *        === 선수금등록 발생(입금)의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         PRV_AM     : 선수금액 
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT20:선수금입금등록 [미등록시:DT20으로 세팅함.])
     *         TR_SQNO    : 거래일련번호 
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 입금 등록
    public void Call_savePrv_Ocr( List<Map<String, Object>> paramList ) throws Exception {
        
        LinkedHashMap<String, Object> tempMap = (LinkedHashMap<String, Object>) paramList.get(0);
        // 공통코드 조회 : 지급결제구분코드 '대체'코드 조회
        String strPyStlDsc = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200Comnc_01", tempMap);
        
        for( Map<String, Object> map : paramList ) {
            map.put("REG_DSC", "N");
            if( map.get("PY_STL_DSC") == null ) {
                map.put("PY_STL_DSC", strPyStlDsc);
            }
        }
        
        this.Call_savePrv(paramList);
    }

    /**
     * 선수금 (입금)등록[/정정/삭제] 한다.
     * @param paramList(채무_선수금기본 등록)
     *        === 선수금등록 사용의 경우 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         SLP_NML_YN : 전표정상여부(Y.정상,N.삭제)
     *         RLTR_DT    : 실거래일자
     *         ACG_DT     : 회계일자
     *         ADJPL_C    : 정산처코드
     *         PRV_AM     : 선수금액
     *         PY_STL_DSC : 지급결제구분코드 ([미등록시(null일경우):'대체'코드로 세팅함.])
     *         STL_ACNO   : 결제계좌번호 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         BNK_C      : 은행코드 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         DPR_NM     : 예금주명 ( PY_STL_DSC가 '계좌이체'일 경우 필수값 )
     *         RMK_CNTN   : 비고내용 
     *         TR_BSN_DSC : 거래업무구분코드 (DT21:선수금사용등록 [미등록시:DT21으로 세팅함.])
     *         TR_SQNO    : 거래일련번호
     *         GUSRID     : 사용자ID
     * @return void형
     * @exception Exception
     */
    // 선수금 사용 등록
    public void Call_savePrv_Use( List<Map<String, Object>> paramList ) throws Exception {
        
        LinkedHashMap<String, Object> tempMap = (LinkedHashMap<String, Object>) paramList.get(0);
        // 공통코드 조회 : 지급결제구분코드 '대체'코드 조회
        String strPyStlDsc = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200Comnc_01", tempMap);
        
        for( Map<String, Object> map : paramList ) {
            map.put("REG_DSC", "U");
            if( map.get("PY_STL_DSC") == null ) {
                map.put("PY_STL_DSC", strPyStlDsc);
            }
        }
        
        this.Call_savePrv(paramList);
    }

    
    /* *********************************************************************************** */
    /* ******************************** 채무잔액 ******************************************** */
    /* *********************************************************************************** */
    
    /**
     * 채무잔액을 조회한다.
     * @param paramList
     *        === 채무잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         TRPL_C     : 거래처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         ADJPL_C    : 정산처코드(거래처코드나 정산처코드 둘중 하나의 코드만 존재하면 됨)
     *         REG_DT     : 등록일자(기준일자)
     *         PRC_TP_C   : 업무구분코드(처리구분코드 ['1':외상매입금]['2':기타미지급금]['3':선수금])
     * @return long형
     * @exception Exception
     */
    // 채무잔액조회
    public long getDTBac(LinkedHashMap paramMap) throws Exception {
        
        long resultBac = 0;
        
        if( paramMap.get("CORP_C") == null ) {
            throw infoException("USERMSG:[채무잔액조회] 회사코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("BZPL_C") == null ) {
            throw infoException("USERMSG:[채무잔액조회] 사업장코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("TRPL_C") == null && paramMap.get("ADJPL_C") == null ) {
            throw infoException("USERMSG:[채무잔액조회] 거래처/정산처코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("REG_DT") == null ) {
            throw infoException("USERMSG:[채무잔액조회] 기준일자 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("PRC_TP_C") == null ) {
            throw infoException("USERMSG:[채무잔액조회] 업무구분코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("ADJPL_C") == null ) {
            // 거래처코드로 입력된 경우에 대비하여 정산처코드를 조회한다.
            String strAdjplC = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200_One00_AdjplC", paramMap);
            paramMap.put("ADJPL_C", strAdjplC);            // 정산처코드
        }
        
        switch( paramMap.get("PRC_TP_C").toString() ) {
        
        case "1" :     // ['1':외상매입금]
            resultBac = this.getCbamBac(paramMap);
            break;
            
        case "2" :     // ['2':기타미지급금]
            resultBac = this.getEtcUpyBac(paramMap);
            break;
            
        case "3" :     // ['3':선수금]
            resultBac = this.getPrvBac(paramMap);
            break;
            
        default :
            throw infoException("USERMSG:[채무잔액조회] 업무구분코드 정보가 정확하지 않습니다. 전산담당자에게 문의하세요.");
        }
        
        return resultBac;
    }

    /**
     * 외상매입금 잔액을 조회한다.
     * @param paramList
     *        === 외상매입금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 외상매입금 잔액조회
    public long getCbamBac(LinkedHashMap paramMap) throws Exception {
        
        if( paramMap.get("CORP_C") == null ) {
            throw infoException("USERMSG:[외상매입금 잔액조회] 회사코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("BZPL_C") == null ) {
            throw infoException("USERMSG:[외상매입금 잔액조회] 사업장코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("ADJPL_C") == null ) {
            throw infoException("USERMSG:[외상매입금 잔액조회] 정산처코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("REG_DT") == null ) {
            throw infoException("USERMSG:[외상매입금 잔액조회] 기준일자 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }

        // 외상매입금 잔액조회
        long resultBac = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200One01_Cbam_Bac", paramMap);
        egovLogger.debug("외상매입금 잔액: "  + resultBac);
        
        return resultBac;
    }
    
    /**
     * 기타미지급금 잔액을 조회한다.
     * @param paramList
     *        === 기타미지급금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 기타미지급금 잔액 조회
    public long getEtcUpyBac(LinkedHashMap paramMap) throws Exception {
        
        if( paramMap.get("CORP_C") == null ) {
            throw infoException("USERMSG:[기타미지급금 잔액조회] 회사코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("BZPL_C") == null ) {
            throw infoException("USERMSG:[기타미지급금 잔액조회] 사업장코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("ADJPL_C") == null ) {
            throw infoException("USERMSG:[기타미지급금 잔액조회] 정산처코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("REG_DT") == null ) {
            throw infoException("USERMSG:[기타미지급금 잔액조회] 기준일자 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        // 기타미지급금 잔액 조회
        long resultBac = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200One01_EtcUpy_Bac", paramMap);
        egovLogger.debug("기타미지급금 잔액: "  + resultBac);
        
        return resultBac;
    }

    /**
     * 선수금 잔액을 조회한다.
     * @param paramList
     *        === 선수금 잔액 조회 입력정보 ===
     *         CORP_C     : 회사코드 
     *         BZPL_C     : 사업장코드
     *         ADJPL_C    : 정산처코드
     *         REG_DT     : 등록일자(기준일자)
     * @return long형
     * @exception Exception
     */
    // 선수금 잔액 조회
    public long getPrvBac(LinkedHashMap paramMap) throws Exception {
        
        if( paramMap.get("CORP_C") == null ) {
            throw infoException("USERMSG:[선수금 잔액조회] 회사코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("BZPL_C") == null ) {
            throw infoException("USERMSG:[선수금 잔액조회] 사업장코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("ADJPL_C") == null ) {
            throw infoException("USERMSG:[선수금 잔액조회] 정산처코드 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        if( paramMap.get("REG_DT") == null ) {
            throw infoException("USERMSG:[선수금 잔액조회] 기준일자 정보가 입력되지 않았습니다. 전산담당자에게 문의하세요.");
        }
        
        // 선수금 잔액 조회
        long resultBac = sqlSession.selectOne("sfmes.sqlmap.ca.selectCa0200One01_Prv_Bac", paramMap);
        egovLogger.debug("선수금 잔액: "  + resultBac);
        
        return resultBac;
    }
}
