package net.waryss.mtools.bluepurse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.waryss.mtools.bluepurse.entity.param.Param_BucketTypeEntity;

@Entity
@Table(name = "bucket")
public class BucketEntity {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "label")
	private String label;
	@Column(name = "inddefault")
	private boolean inddefault;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	private UserEntity user;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type")
	private Param_BucketTypeEntity type;

	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}
	public Param_BucketTypeEntity getType() {
		return type;
	}
	public UserEntity getUser() {
		return user;
	}
	public boolean isInddefault() {
		return inddefault;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setInddefault(boolean inddefault) {
		this.inddefault = inddefault;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setType(Param_BucketTypeEntity type) {
		this.type = type;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
}
