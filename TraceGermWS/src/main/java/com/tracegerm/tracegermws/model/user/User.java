package com.tracegerm.tracegermws.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tracegerm.tracegermws.model.visitDetails.VisitDetails;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@Column(name = "USERNAME")
	protected String username;
	
	@OneToMany(targetEntity = VisitDetails.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name ="FK_USERNAME")
	private List<VisitDetails> categoryList = new ArrayList<VisitDetails>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<VisitDetails> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<VisitDetails> categoryList) {
		this.categoryList = categoryList;
	}
	

}
