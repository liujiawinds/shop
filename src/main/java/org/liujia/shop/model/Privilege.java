package org.liujia.shop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="privilege")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Privilege {
	@Id
	private String role;
	private String operation;
	
	public String getRole() {
		return role;
	}
	public String getOperation() {
		return operation;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
