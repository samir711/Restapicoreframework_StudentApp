package com.studentapp.utils;

import java.util.Random;

public class TestUtlis {
	
	
	public static String  getRandomValue()
	{
		Random random = new Random();
		int randomInt = random.nextInt(100000);  //892468 
		return Integer.toString(randomInt);
		
	}

}
