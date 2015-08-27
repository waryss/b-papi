package net.waryss.mtools.bluepurse.dto;

public class BucketDto {

	private Long bucketId;
	private String label;
	private UserDto creator;
	private boolean inddefault;
	private Integer type;

	public Long getBucketId() {
		return bucketId;
	}
	public UserDto getCreator() {
		return creator;
	}
	public String getLabel() {
		return label;
	}
	public Integer getType() {
		return type;
	}
	public boolean isInddefault() {
		return inddefault;
	}
	public void setBucketId(Long bucketId) {
		this.bucketId = bucketId;
	}
	public void setCreator(UserDto creator) {
		this.creator = creator;
	}
	public void setInddefault(boolean inddefault) {
		this.inddefault = inddefault;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setType(Integer type) {
		this.type = type;
	}


}
