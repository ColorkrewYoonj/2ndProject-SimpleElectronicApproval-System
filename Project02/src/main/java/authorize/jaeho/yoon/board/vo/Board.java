package authorize.jaeho.yoon.board.vo;

public class Board {
	 private String signState_id;
	 private String signState;
     private String department_name;
     private String department_id;
     private String employee_name;
     private String draftDate;
     private String employee_id;
     private String manager_id;
     private String doc_name;
     private int doc_id;
     private String doc_content;
     private String isPersonal;
     private String[] content_real;
     private String f_sign;
     private String s_sign;
     private String t_sign;
     
     
     
     
	public String[] getContent_real() {
		return content_real;
	}
	public void setContent_real(String[] content_real) {
		this.content_real = content_real;
	}
	public String getSignState_id() {
		return signState_id;
	}
	public void setSignState_id(String signState_id) {
		this.signState_id = signState_id;
	}
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(String draftDate) {
		this.draftDate = draftDate;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public int getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_content() {
		return doc_content;
	}
	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}
	public String getIsPersonal() {
		return isPersonal;
	}
	public void setIsPersonal(String isPersonal) {
		this.isPersonal = isPersonal;
	}
	public String getF_sign() {
		return f_sign;
	}
	public void setF_sign(String f_sign) {
		this.f_sign = f_sign;
	}
	public String getS_sign() {
		return s_sign;
	}
	public void setS_sign(String s_sign) {
		this.s_sign = s_sign;
	}
	public String getT_sign() {
		return t_sign;
	}
	public void setT_sign(String t_sign) {
		this.t_sign = t_sign;
	}
	public Board(){
		
	}

	
	public Board(String signState_id, String signState, String department_name, String department_id,
			String employee_name, String draftDate, String employee_id, String manager_id, String doc_name, int doc_id,
			String doc_content, String isPersonal, String[] content_real, String f_sign, String s_sign, String t_sign) {
		super();
		this.signState_id = signState_id;
		this.signState = signState;
		this.department_name = department_name;
		this.department_id = department_id;
		this.employee_name = employee_name;
		this.draftDate = draftDate;
		this.employee_id = employee_id;
		this.manager_id = manager_id;
		this.doc_name = doc_name;
		this.doc_id = doc_id;
		this.doc_content = doc_content;
		this.isPersonal = isPersonal;
		this.content_real = content_real;
		this.f_sign = f_sign;
		this.s_sign = s_sign;
		this.t_sign = t_sign;
	}
	@Override
	public String toString() {
		return "Board [signState_id=" + signState_id + ", department_name=" + department_name + ", department_id="
				+ department_id + ", employee_name=" + employee_name + ", draftDate=" + draftDate + ", employee_id="
				+ employee_id + ", manager_id=" + manager_id + ", doc_name=" + doc_name + ", doc_id=" + doc_id
				+ ", doc_content=" + doc_content + ", isPersonal=" + isPersonal + ", f_sign=" + f_sign + ", s_sign="
				+ s_sign + ", t_sign=" + t_sign + "]";
	}
}
