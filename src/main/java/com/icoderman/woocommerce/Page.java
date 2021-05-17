package com.icoderman.woocommerce;

import java.util.List;

public class Page<T> {

	/**
	 * List of element.
	 */
	private List<T> content;
	/**
	 * Total number of element.
	 */
	private int total;
	/**
	 * Total number of pages.
	 */
	private int totalPages;
	/**
	 * Size of the current page.
	 */
	private int size;
	
	/**
	 * Create new page of element.
	 * @param content Content list
	 * @param total total number of element.
	 * @param totalPages total number of pages.
	 * @param size size of the current result.
	 */
	public Page(List<T> content, int total, int totalPages, int size) {
		this.content = content;
		this.total = total;
		this.totalPages = totalPages;
		this.size = size;
	}
	
	public List<T> getContent() {
		return content;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public int getSize() {
		return size;
	}
}
