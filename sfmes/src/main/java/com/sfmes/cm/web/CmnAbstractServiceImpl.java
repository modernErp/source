package com.sfmes.cm.web;

import java.util.Locale;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.trace.LeaveaTrace;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 비즈니스 서비스 구현체가 상속받는 추상클래스
 * <p><b>NOTE:</b> 비즈니스 서비스 구현시 디폴드로 Exception 발생을 위한 processException 메소드와
 * leaveaTrace 메소드를 가지고 있다. processException / leaveaTrace 를 여러스타일의 파라미터를 취할 수 있도록 제공하고 있다.
 * 또한 EgovAbstractServiceImpl을 상속하는 클래스는 직접 Logger 생성없이
 * protected로 선언된 egovLogger를 사용할 수 있다.</p>
 * 
 * @author Daniela Kwon
 * @since 2014.06.01
 * @version 3.0
 * @see <pre>
 * 
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일			수정자				수정내용
 *  ---------------------------------------------------------------------------------
 *   2014.06.01	Daniela Kwon		최초생성
 *   2020.06.09 이철홍              infoException 추가함
 * 
 * </pre>
 */
public abstract class CmnAbstractServiceImpl {

	protected Logger egovLogger = LoggerFactory.getLogger(CmnAbstractServiceImpl.class);

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource(name = "leaveaTrace")
	private LeaveaTrace traceObj;
	
	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지내용
	 * @return Exception EgovBizException 객체
	 */
	protected Exception infoException(final String msgStr) {
		return processException("usr.error", new String[] {msgStr});
	}

	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey) {
		return processException(msgKey, new String[] {});
	}
	
	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey, Exception e) {
		return processException(msgKey, new String[] {}, e);
	}

	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey, final String[] msgArgs) {
		return processException(msgKey, msgArgs, null);
	}

	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey, final String[] msgArgs, final Exception e) {
		return processException(msgKey, msgArgs, e, LocaleContextHolder.getLocale());
	}

	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey, final String[] msgArgs, final Exception e, Locale locale) {
		return processException(msgKey, msgArgs, e, locale, null);
	}

	/**
	 * EgovBizException 발생을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param exception 발생한 Exception(내부적으로 취하고 있다가 에러핸들링시 사용)
	 * @param locale 명시적 국가/언어지정
	 * @param exceptionCreator 외부에서 별도의 Exception 생성기 지정
	 * @return Exception EgovBizException 객체
	 */
	protected Exception processException(final String msgKey, final String[] msgArgs, final Exception e, final Locale locale, ExceptionCreator exceptionCreator) {
		ExceptionCreator eC = null;
		if (exceptionCreator == null) {
			eC = new ExceptionCreator() {
				public Exception createBizException(MessageSource messageSource) {
					return new EgovBizException(messageSource, msgKey, msgArgs, locale, e);
				}
			};
		} else {
			eC = exceptionCreator;
		}
		
		return eC.createBizException(messageSource);
	}

	protected interface ExceptionCreator {
		Exception createBizException(MessageSource messageSource);
	}

	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 */
	protected void leaveaTrace(String msgKey) {
		leaveaTrace(msgKey, new String[] {});
	}

	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 */
	protected void leaveaTrace(String msgKey, String[] msgArgs) {
		leaveaTrace(msgKey, msgArgs, null);
	}

	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드.
	 * 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들
	 * @param locale 명시적 국가/언어지정
	 */
	protected void leaveaTrace(String msgKey, String[] msgArgs, Locale locale) {
		traceObj.trace(this.getClass(), messageSource, msgKey, msgArgs, locale, egovLogger);
	}
}
