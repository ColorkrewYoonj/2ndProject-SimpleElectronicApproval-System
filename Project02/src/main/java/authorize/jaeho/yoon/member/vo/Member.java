package authorize.jaeho.yoon.member.vo;

public class Member {
	private String employee_ID;
	private String employee_name;
    private String department_ID;
    private String employee_email;
    private String manager_ID;
    private String password;
	public String getEmployee_ID() {
		return employee_ID;
	}
	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDepartment_ID() {
		return department_ID;
	}
	public void setDepartment_ID(String department_ID) {
		this.department_ID = department_ID;
	}
	public String getEmployee_email() {
		return employee_email;
	}
	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}
	public String getManager_ID() {
		return manager_ID;
	}
	public void setManager_ID(String manager_ID) {
		this.manager_ID = manager_ID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Member(){
	}
	
	public Member(String employee_ID, String employee_name, String department_ID, String employee_email,
			String manager_ID, String password) {
		super();
		this.employee_ID = employee_ID;
		this.employee_name = employee_name;
		this.department_ID = department_ID;
		this.employee_email = employee_email;
		this.manager_ID = manager_ID;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [employee_ID=" + employee_ID + ", employee_name=" + employee_name + ", department_ID="
				+ department_ID + ", employee_email=" + employee_email + ", manager_ID=" + manager_ID + ", password="
				+ password + "]";
	}
}
