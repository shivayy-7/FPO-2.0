package com.fpo.web.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayToListConversion {
	
	ArrayToListConversion(){}
	
	public static <T> List<T> ConvertToList(T arr[])   
	{   
	List<T> list = new ArrayList<>();   
	Collections.addAll(list, arr);   
	return list;   
	}   
	
}
