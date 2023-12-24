package com.polling.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author venkat
 */


@Entity
public class PollSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String sessionName;
	private String userName;

	@Column(name = "START_DATE", columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date createdDate;

	@OneToMany(targetEntity = Options.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pollSession")
	private List<Options> options;


	public PollSession() {
	}

	public PollSession(Boolean isActive, String userName, Date createdDate) {
		super();
		this.userName = userName;
		this.createdDate = createdDate;
		this.isActive = isActive;
	}



	private Boolean isActive = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
