package com.empl.mgr.constant;

public enum MethodType {

	FIND(1), DELETE(2), MODIFY(3), ADD(4);

	private int id;

	private MethodType() {
		// TODO Auto-generated constructor stub
	}

	private MethodType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
