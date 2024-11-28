package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.subjectMVCProject.model.LessonVO;

import publicDataTest.PublicDataAPITest;
import publicDataTest.model.LandPriceVO;

public class LandPriceRegisterManager {
	public Scanner sc = new Scanner(System.in); 
	//과목등록(insert)
	public void insertManager() {
		LandPriceDAO ldao = new LandPriceDAO(); 
		boolean successFlag = false;  
		//네트워크로 부터 데이터를 입력받는다.
		ArrayList<LandPriceVO> landPricelist = PublicDataAPITest.apiDataLoad(); 
		
		for(LandPriceVO lvo : landPricelist) {
			int count = ldao.landPriceCheckNodeNOSelect(lvo);
			if(count <= 0) {
				successFlag = ldao.landPriceInsert(lvo);
			}else {
				successFlag = ldao.landPriceUpdate(lvo); 
			}
		}
		
		//화면출력
		if(successFlag == true) {
			System.out.println("데이터를  입력 또는 수정 하였습니다.");
		}else {
			System.out.println("데이터를  입력 또는 수정 실패 하였습니다.");
		}
	} 	
	//과목목록(select)
	public void selectManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 
	//과목삭제(delete)
	public void deleteManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();

		//전체리스트를 보여준다.
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		//화면으로부터 입력받는다. 
		System.out.print("삭제할번호>>");
		int no = Integer.parseInt((sc.nextLine()).trim());
		System.out.println("no="+no);
		LessonVO lvo2 = new LessonVO(); 
		lvo2.setNo(no);
		System.out.println("lvo2="+lvo2.toString());
		boolean successFlag = ldao.lessonDelete(lvo2); 
		
		//화면출력
		if(successFlag == true) {
			System.out.println(no +"번호를 삭제 하였습니다.");
		}else {
			System.out.println(no +"번호 삭제 실패하였습니다.");
		}
	} 
	//과목수정(update)
	public void updateManager() {
		LessonDAO ldao = new LessonDAO();
		LessonVO lvo = new LessonVO();
		//수정하기 전체출력요청
		ArrayList<LessonVO> lessonList = ldao.lessonSelect(lvo); 
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		//화면으로부터 입력받는다.
		System.out.print("수정할 번호선택>>");
		int no = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할과목입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String abbre= (sc.nextLine()).trim();

		System.out.print("수정할과목명입력(O-운영 ,A-어셈블,C-컴파일,J-자료,P-프로밍,D-디비 S-소프공학)>>");
		String name = (sc.nextLine()).trim();
		
		lvo = new LessonVO(no, abbre, name); 
		boolean successFlag = ldao.lessonUpdate(lvo); 
		
		//화면출력
		if(successFlag == true) {
			System.out.println(no+"과목을 수정 하였습니다.");
		}else {
			System.out.println(no +"과목을 수정 실패 하였습니다.");
		}
	} 	
	//과목정렬(select)
	public void selectSortManager() {
		LessonDAO ldao = new LessonDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LessonVO lvo = new LessonVO();
		ArrayList<LessonVO> lessonList = ldao.lessonSelectSort(lvo); 
		//화면출력
		if(lessonList.size() != 0) {
			printLessonList(lessonList); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 
	
	//화면출력
	public void printLessonList(ArrayList<LessonVO> lessonList) {
		for( LessonVO data : lessonList ) {
			System.out.println(data);
		}
	}

}





