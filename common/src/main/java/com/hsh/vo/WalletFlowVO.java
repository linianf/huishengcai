package com.hsh.vo;

public class WalletFlowVO {

    private  String  tradeName;//交易类型名称
	
	private  long  amount;//交易金额
	
	private  int  inOut;//收益还是支出
	
	private  String  operateDate;//交易发生时间

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getInOut() {
		return inOut;
	}

	public void setInOut(int inOut) {
		this.inOut = inOut;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
}
