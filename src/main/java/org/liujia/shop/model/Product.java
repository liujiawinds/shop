package org.liujia.shop.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="Product")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = -5029733386359418824L;
	private Integer id;
	private String name;
	private Category category;
	private float price;
	private String description;
	private String designer;
	private String addDate;
	private Integer amount;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	@ManyToOne
	@JoinColumn(nullable=false)
	public Category getCategory() {
		return category;
	}
	
	
	
	@Column(nullable=false)
	public float getPrice() {
		return price;
	}
	
	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="add_time")
	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		final Product other = (Product) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		return true;
	}
	
}
