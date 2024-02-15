package com.fpo.web.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueListsCall {

	UniqueListsCall(){}
	
	public static <T> List<T> FindDifference(List<T> firstList , List<T> scndList) {
		
		Set<T> ad = new HashSet<T>(firstList);
		Set<T> bd = new HashSet<T>(scndList);
		ad.removeAll(bd);
		
		return ad.stream().collect(Collectors.toList());

	}
}
