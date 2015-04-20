package com.cattsoft.pub.util;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class LimitedLinkedList extends LinkedList{
	
	public static int COLLECTION_SIZE_MAX = 9999; // 集合最大记录数限制
	public static Logger log = Logger.getLogger(LimitedLinkedList.class);
	
	
	 
	//@Override
	public void add(int index, Object element) {
		int maxSize = COLLECTION_SIZE_MAX;
		if(this.size() >= maxSize){
			throw new IndexOutOfBoundsException("集合中的记录数超最大数("+maxSize+")限制！");
		}
		super.add(index, element);
	}

	//@Override
	public boolean add(Object o) {
		int maxSize = COLLECTION_SIZE_MAX;
		if(this.size() >= maxSize){
			throw new IndexOutOfBoundsException("集合中的记录数超最大数("+maxSize+")限制！");
		}
		return super.add(o);
	}

	//@Override
	public boolean addAll(Collection c) {
		int maxSize = COLLECTION_SIZE_MAX;
		if(c != null){
			if(this.size() + c.size() >= maxSize){
				throw new IndexOutOfBoundsException("集合中的记录数超最大数("+maxSize+")限制！");
			}
		}
		return super.addAll(c);
	}

	//@Override
	public boolean addAll(int index, Collection c) {
		int maxSize = COLLECTION_SIZE_MAX;
		if(c != null){
			if(this.size() + c.size() >= maxSize){
				throw new IndexOutOfBoundsException("集合中的记录数超最大数("+maxSize+")限制！");
			}
		}
		return super.addAll(index, c);
	}

}
