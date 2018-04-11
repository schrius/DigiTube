package CustomerInfo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CustomerGroup {
	@Id
	private long groupdID;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "groupNumber")
	private Set<Customer> customerGroup;
	@OneToOne
	private Customer groupParent;
	@Column(length = 16)
	private String groupPlan;

	public CustomerGroup() {
		super();
	}
	
	public CustomerGroup(long groupdID, Set<Customer> customerGroup, Customer groupParent, String groupPlan) {
		super();
		this.groupdID = groupdID;
		this.customerGroup = customerGroup;
		this.groupParent = groupParent;
		this.groupPlan = groupPlan;
	}


	public long getGroupdID() {
		return groupdID;
	}

	public void setGroupdID(long groupdID) {
		this.groupdID = groupdID;
	}

	public Set<Customer> getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(Set<Customer> customerGroup) {
		this.customerGroup = customerGroup;
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
