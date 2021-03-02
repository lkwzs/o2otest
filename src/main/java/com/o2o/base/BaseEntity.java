package com.o2o.base;

public class BaseEntity<PK> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;


	
	
	private PK id;

	public PK getId() {
		return id;
	}
	public void setId(PK id) {
		this.id = id;
	}
	
}
