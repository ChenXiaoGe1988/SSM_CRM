package com.ckb.ssm.util;

import java.util.UUID;

/**
 * @author ychen
 * 生成唯一id
 */
public class UUIDUtil {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-","");
		
	}
	
}
