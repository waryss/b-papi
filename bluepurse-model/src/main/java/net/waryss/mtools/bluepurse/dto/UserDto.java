package net.waryss.mtools.bluepurse.dto;

import java.sql.Date;

public class UserDto implements BluePurseBean {

	private Long userId;

	private String email;

	private String login;

	private String firstname;

	private String lastname;

	private Integer userGroup;

	private Integer status;

	private Date subscriptionDate;

	private Date unsubscriptionDate;

	private Date lastmodificationDate;

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public Date getLastmodificationDate() {
		return lastmodificationDate;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLogin() {
		return login;
	}

	public Long getPurser() {
		return userId;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public Date getUnsubscriptionDate() {
		return unsubscriptionDate;
	}

	public Integer getUserGroup() {
		return userGroup;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastmodificationDate(Date lastmodificationDate) {
		this.lastmodificationDate = lastmodificationDate;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPurser(Long purser) {
		userId = purser;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public void setUnsubscriptionDate(Date unsubscriptionDate) {
		this.unsubscriptionDate = unsubscriptionDate;
	}

	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}
}
