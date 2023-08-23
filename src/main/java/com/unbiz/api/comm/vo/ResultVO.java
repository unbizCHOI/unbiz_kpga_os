package com.unbiz.api.comm.vo;

import com.unbiz.api.comm.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

@ApiModel(description = "ResultVO")
public class ResultVO extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/** status code */
	public static final String CODE_TAG = "code";

	/** Return content */
	public static final String MSG_TAG = "msg";

	/** Data object */
	public static final String DATA_TAG = "data";


	public enum Type {
		SUCCESS(0),
		ERROR(500);
		private final int value;
		Type(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}
	}
	public enum Msg {
		SUCCESS("정상 처리 되었습니다."),
		ERROR("처리 중 오류가 발생하였습니다.");
		private final String value;

		Msg(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}

	}
	/**
	 * Initialize a newly created AjaxResult object
	 * so that it represents an empty message
	 */
	public ResultVO() {
	}

	/**
	 * Initialize a newly created AjaxResult object
	 * 
	 * @param type State type
	 * @param msg  Return content
	 */
	public ResultVO(Type type, String msg) {
		super.put(CODE_TAG, type.value);
		super.put(MSG_TAG, msg);
	}

	/**
	 * Initialize a newly created AjaxResult object
	 * 
	 * @param type State type
	 * @param msg  Return content
	 * @param data Data object
	 */
	public ResultVO(Type type, String msg, Object data) {
		super.put(CODE_TAG, type.value);
		super.put(MSG_TAG, msg);
		if (!ObjectUtils.isEmpty(data)) {
			super.put(DATA_TAG, data);
		} else {
			super.put(CODE_TAG, 10);
		}
	}

	/**
	 * Return success message
	 * 
	 * @return Success message
	 */
	public static ResultVO success() {
		return ResultVO.success(Msg.SUCCESS);
	}

	/**
	 * Return success message
	 * 
	 * @return Success message
	 */
	public static ResultVO success(Object data) {
		return ResultVO.success(Msg.SUCCESS.value, data);
	}

	/**
	 * Return success message
	 * 
	 * @param msg Return content
	 * @return Success message
	 */
	public static ResultVO success(String msg) {
		return ResultVO.success(msg, null);
	}

	/**
	 * Return success message
	 * 
	 * @param msg  Return content
	 * @param data Data object
	 * @return Success message
	 */
	public static ResultVO success(String msg, Object data) {
		return new ResultVO(Type.SUCCESS, msg, data);
	}




	/**
	 * Return error message
	 *
	 * @return
	 */
	public static ResultVO error() {
		return ResultVO.error(Msg.ERROR.value);
	}

	/**
	 * Return error message
	 *
	 * @param msg Return content
	 * @return Error message
	 */
	public static ResultVO error(String msg) {
		return ResultVO.error(msg, null);
	}

	public static ResultVO error(Object data) {
		return ResultVO.error(Msg.ERROR.value, data);
	}

	/**
	 * Return error message
	 *
	 * @param msg  Return content
	 * @param data Data object
	 * @return Error message
	 */
	public static ResultVO error(String msg, Object data) {
		return new ResultVO(Type.ERROR, msg, data);
	}

	public boolean isSuccess() {
		return (int) this.get(CODE_TAG) == Type.SUCCESS.value;
	}

	public String getMsg() {
		return this.get(MSG_TAG).toString();
	}
}
