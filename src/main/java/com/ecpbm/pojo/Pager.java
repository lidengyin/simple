package com.ecpbm.pojo;

public class Pager {

	//��ǰҳ
	//��ǰҳ����
	//������
	//��ҳ��
	private Integer curPager;
	private Integer perPageRows;
	private Integer rowCount;
	private int pageCount;
	private int firstLimitPage;
	private int desc;
	public int getDesc() {
		return desc;
	}
	public void setDesc(int desc) {
		this.desc = desc;
	}
	public Integer getCurPager() {
		return curPager;
	}
	public void setCurPager(Integer curPager) {
		System.out.println("��ǰҳ��" + this.curPager);
		this.curPager = curPager;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public void setFirstLimitPage(Integer firstLimitPage) {
		this.firstLimitPage = firstLimitPage;
	}
	public Integer getPerPageRows() {
		return perPageRows;
	}
	public void setPerPageRows(Integer perPageRows) {
		this.perPageRows = perPageRows;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		System.out.println("��ҳ�� " + (this.rowCount + this.perPageRows - 1) / this.perPageRows);
		return (this.rowCount + this.perPageRows - 1) / this.perPageRows;
	}
	public int getFirstLimitPage() {
		return (this.curPager - 1) * this.perPageRows;
	}

	
	
}
