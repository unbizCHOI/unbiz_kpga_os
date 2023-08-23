package com.unbiz.api.comm.config;

import java.util.ArrayList;
import com.unbiz.api.comm.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * packageName    : com.unbiz.coda.comm.config
 * fileName       : ExceptionHandler
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : 오류 핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
@ControllerAdvice
public class ExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler( Exception.class )
	public ResponseEntity<Object> handleAll(Exception ex) {
		printException(ex);
		ResultVO response = new ResultVO().error();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	// 파라메터 값 오류
	@org.springframework.web.bind.annotation.ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity<Object> handleUnexpectedTypeException(MethodArgumentNotValidException ex) {
		printException(ex);
		
		BindingResult bindingResult = ex.getBindingResult();
		ArrayList<String> excepArr = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String text = "[ " +fieldError.getField()+" ] 필드를 확인해주세요. 입력된 값: [ "+ fieldError.getRejectedValue()+" ]";
			excepArr.add(text);
		}
		ResultVO response = new ResultVO().error(excepArr);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	// 파라메터 필수값 없음
	@org.springframework.web.bind.annotation.ExceptionHandler( MissingServletRequestParameterException.class )
	public ResponseEntity<Object> handleUnexpectedTypeException(MissingServletRequestParameterException ex) {
		printException(ex);
		String text = "[ " +ex.getParameterName()+" ]는 필수값 입니다.";
		ResultVO response = new ResultVO().error(text);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	// 파라메터 타입 오류
	@org.springframework.web.bind.annotation.ExceptionHandler( MethodArgumentTypeMismatchException.class )
	public ResponseEntity<Object> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		printException(ex);
		String classType = "";
		if(int.class == ex.getRequiredType()){
			classType = "숫자형";
		} else if(String.class == ex.getRequiredType()){
			classType = "문자형";
		} 
		String text = "[ " + ex.getName() +" ]는 " + classType + "만 가능합니다.";
		ResultVO response = new ResultVO().error(text);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	private void printException(Exception ex) {
		logger.info("==========================================================================================================");
		ex.printStackTrace();
		logger.info("==========================================================================================================");
	}
}
