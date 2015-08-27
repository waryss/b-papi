package net.waryss.mtools.bluepurse.dto;

import java.sql.Date;
import java.util.Set;

public class PurseDto {

	private Long purseId;

	private UserDto creator;

	private Set<UserDto> contribution;

	private Date creationDate;

	private Date closeDate;

	public PurseDto() {}

	public PurseDto(Long purseId) {
		this.purseId = purseId;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public Set<UserDto> getContribution() {
		return contribution;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public UserDto getCreator() {
		return creator;
	}

	public Long getPurseId() {
		return purseId;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public void setContribution(Set<UserDto> contribution) {
		this.contribution = contribution;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}

	public void setPurseId(Long purseId) {
		this.purseId = purseId;
	}
}
