package com.RmgYantraFramework.GenericUtils;

import java.util.Random;

public class JavaUtils
{
	public static int getrandomdata()
	{
		Random rand=new Random();
		return rand.nextInt(1000);
	}

}
