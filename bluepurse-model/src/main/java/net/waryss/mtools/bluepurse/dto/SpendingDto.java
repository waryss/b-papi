package net.waryss.mtools.bluepurse.dto;

public class SpendingDto extends PurseItemDto{

	private Long spendingId;

	public SpendingDto() {
		super();
	}

	public SpendingDto(Long spendingId) {
		this.spendingId = spendingId;
	}

	public Long getSpendingId() {
		return spendingId;
	}

	public void setSpendingId(Long spendingId) {
		this.spendingId = spendingId;
	}

}
