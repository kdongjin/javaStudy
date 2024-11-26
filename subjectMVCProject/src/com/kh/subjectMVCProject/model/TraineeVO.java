package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	private int no; 		//--pk seq
	private String s_num; 	//--student.num(fk) 학생번호
	private String abbre; 	//--lesson.abbre(fk) 과목요약
	private String section; //--전공,부전공,교양
	private Date registDate;//--수강신청일
	//생성자
	public TraineeVO() {}
	public TraineeVO(String s_num, String abbre, String section) {
		super();
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
	}
	public TraineeVO(int no, String s_num, String abbre, String section, Date registDate) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.registDate = registDate;
	}
	//getter, setter
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
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	//toString
	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section
				+ ", registDate=" + registDate + "]";
	}
}
