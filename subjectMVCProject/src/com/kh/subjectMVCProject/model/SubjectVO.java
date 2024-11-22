package com.kh.subjectMVCProject.model;

public class SubjectVO {
	private int no; 			//-- pk, seq
	private String num; 		//-- 학과번호 01, 02, 03,04,05
	private String name; 		//-- 학과이름
	public SubjectVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubjectVO(String num, String name) {
		super();
		this.num = num;
		this.name = name;
	}
	public SubjectVO(int no, String num, String name) {
		super();
		this.no = no;
		this.num = num;
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "[no=" + no + ", num=" + num + ", name=" + name + "]";
	}
	
	
}
