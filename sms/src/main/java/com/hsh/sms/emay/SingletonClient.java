package com.hsh.sms.emay;



public class SingletonClient {
	private static Client hospitalClient=null;
	private static Client hshClient=null;
	private static Client hscClient=null;
	private SingletonClient(){
	}
	public synchronized static Client getHospitalClient(String softwareSerialNo,String key){
		if(hospitalClient==null){
			try {
				hospitalClient=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hospitalClient;
	}
	
	public synchronized static Client getHshClient(String softwareSerialNo,String key){
		if(hshClient==null){
			try {
				hshClient=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hshClient;
	}
	
	public synchronized static Client getHscClient(String softwareSerialNo,String key){
		if(hscClient==null){
			try {
				hscClient=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hscClient;
	}
	
	
	
	
	
}
