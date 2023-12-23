package com.polling.restaurant.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Venkat
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String option;
    private String userName;
    private boolean isSelected = false;
    

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public PollSession getPollSession() {
		return pollSession;
	}

	public void setPollSession(PollSession pollSession) {
		this.pollSession = pollSession;
	}

	public Options() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Options(String option, String userName, PollSession pollSession) {
		super();
		this.option = option;
		this.userName = userName;
		this.pollSession = pollSession;
	}

	@ManyToOne
    private PollSession pollSession; 
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PollSession getSession() {
		return pollSession;
	}

	public void setSession(PollSession pollSession) {
		this.pollSession = pollSession;
	}

}
