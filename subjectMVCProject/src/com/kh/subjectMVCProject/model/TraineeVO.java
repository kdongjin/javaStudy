package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	private int no; 		//--pk seq
	private String s_num;  	//--student(fk) 학생번호
	private String abbre; 	//--lesson(fk) 과목요약
	private String section; //--전공,부전공,교양
	private Date tdate; 	//--수강신청일
	public TraineeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TraineeVO(int no, String s_num, String abbre, String section, Date tdate) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.tdate = tdate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	public String getAbbre() {
		return abbre;
	}
	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	@Override
	public String toString() {
		return "[no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section + ", tdate="
				+ tdate + "]";
	}
}
