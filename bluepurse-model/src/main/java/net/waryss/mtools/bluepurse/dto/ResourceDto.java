package net.waryss.mtools.bluepurse.dto;

public class ResourceDto extends PurseItemDto implements BluePurseBean{

	private Long resourceId;

	public ResourceDto() {
		super();
	}

	public ResourceDto(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}


}
