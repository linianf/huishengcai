package com.hsh.vo;
/**
 * 消息定义：code 2客户端需要强制升级，3会话失效
 * @author lnf
 *
 */
public class AjaxResult {
	public static final int CODE_SUCCESS = 1;
	public static final int CODE_FAILED = 0;
	private int status;
	private String describe;
	private Object obj;

	private AjaxResult(int status, Object obj, String describe) {
		this.status = status;
		this.describe = describe;
		this.obj = obj;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public static final AjaxResult failed() {
		return new AjaxResult(CODE_FAILED, null, null);
	}

	public static final AjaxResult failed(String describe) {
		return new AjaxResult(CODE_FAILED, null, describe);
	}

	public static final AjaxResult failed(Object obj, String describe) {
		return new AjaxResult(CODE_FAILED, obj, describe);
	}
	
	public static final AjaxResult success(Object obj) {
		return new AjaxResult(CODE_SUCCESS, obj, "ok");
	}
	
	public static final AjaxResult success() {
        return new AjaxResult(CODE_SUCCESS, null, null);
    }

    public static final AjaxResult success(Object data, String message) {
        return new AjaxResult(CODE_SUCCESS, data, message);
    }
	
}
