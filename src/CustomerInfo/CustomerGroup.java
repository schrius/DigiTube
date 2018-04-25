package CustomerInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CustomerGroup")
public class CustomerGroup {
	@Id
	@Column(name = "Group_ID")
	private long groupdID;
	@OneToOne
	@JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "Group_Parent_ID_FK"))
	private Customer groupParent;
	@Column(length = 16)
	private String groupPlan;
	
	public CustomerGroup() {
	}
	public long getGroupdID() {
		return groupdID;
	}
	public void setGroupdID(long groupdID) {
		this.groupdID = groupdID;
	}
	public Customer getGroupParent() {
		return groupParent;
	}
	public void setGroupParent(Customer groupParent) {
		this.groupParent = groupParent;
	}
	public String getGroupPlan() {
		return groupPlan;
	}
	public void setGroupPlan(String groupPlan) {
		this.groupPlan = groupPlan;
	}
}
