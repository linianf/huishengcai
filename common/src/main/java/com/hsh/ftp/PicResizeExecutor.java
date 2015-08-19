package com.hsh.ftp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PicResizeExecutor {

	private static ExecutorService executors = Executors.newFixedThreadPool(5);
	
	public static void  doJob(PicResizeTask task){
		executors.execute(task);
	}
}
