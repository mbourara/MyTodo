package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util 
{
	public static String cryptageMD5(String mdp)
	{
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(mdp.getBytes());
			byte[] md5 = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : md5) 
				sb.append(Integer.toHexString((int) (b & 0xff)));
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}