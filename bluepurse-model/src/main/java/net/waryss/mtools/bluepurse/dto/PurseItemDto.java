package net.waryss.mtools.bluepurse.dto;

import java.sql.Date;

public abstract class PurseItemDto implements BluePurseBean{

	private String label;

	private float amount;

	private Integer recurrency;

	private Long purseId;

	private BucketDto bucket;

	private UserDto owner;

	private Date creationDate;

	private Date updateDate;

	private Date deleteDate;

	public float getAmount() {
		return amount;
	}

	public BucketDto getBucket() {
		return bucket;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public String getLabel() {
		return label;
	}

	public UserDto getOwner() {
		return owner;
	}

	public Long getPurseId() {
		return purseId;
	}

	public Integer getRecurrency() {
		return recurrency;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setBucket(BucketDto bucket) {
		this.bucket = bucket;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}

	public void setPurseId(Long purseId) {
		this.purseId = purseId;
	}

	public void setRecurrency(Integer recurrency) {
		this.recurrency = recurrency;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


}
