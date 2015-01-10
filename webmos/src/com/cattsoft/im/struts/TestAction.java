package com.cattsoft.im.struts;

import java.util.ArrayList;
import java.util.List;

public class TestAction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List a=new ArrayList();
		a.add("111");
		a.add("222");
		
		List b=new ArrayList();
		b.addAll(a);
		b.add("333");
		System.out.println(a.size());
		System.out.println(b.size());
		for(int i=0;i<b.size();i++) {
			System.out.println((String)b.get(i));
		}

	}

}
