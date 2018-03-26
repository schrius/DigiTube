package CustomerInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerGroup {
	@Id
	@Column(name="GROUP_ID")
	private long groupdID;
	private long parentID;
	
	@Column(length = 16)
	private String groupPlan;

	public CustomerGroup() {
		super();
	}

	public CustomerGroup(long groupdID, long parentID, String groupPlan) {
		super();
		this.groupdID = groupdID;
		this.parentID = parentID;
		this.groupPlan = groupPlan;
	}


	public long getGroupdID() {
		return groupdID;
	}
	public void setGroupdID(long groupdID) {
		this.groupdID = groupdID;
	}
	public long getParentID() {
		return parentID;
	}
	public void setParentID(long parentID) {
		this.parentID = parentID;
	}
	public String getGroupPlan() {
		return groupPlan;
	}
	public void setGroupPlan(String groupPlan) {
		this.groupPlan = groupPlan;
	}
	
}
