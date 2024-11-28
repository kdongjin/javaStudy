package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
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
		
		try {
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
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	} 	
	//과목목록(select)
	public void selectManager() {
		LandPriceDAO ldao = new LandPriceDAO(); 
		//화면으로부터 입력받는다.
		//데이타베이스 요청
		LandPriceVO lvo = new LandPriceVO(); 
		ArrayList<LandPriceVO> list = ldao.landPriceSelect();
		//화면출력
		if(list.size() != 0) {
			printLessonList(list); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	} 
	//과목삭제(delete)
	public void deleteManager() {
		LandPriceDAO ldao = new LandPriceDAO(); 
		//전체리스트를 보여준다.
		ArrayList<LandPriceVO> list = ldao.landPriceSelect(); 
		if(list.size() != 0) {
			printLessonList(list); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		//화면으로부터 입력받는다. 
		System.out.print("삭제할번호>>");
		int nodeno = Integer.parseInt((sc.nextLine()).trim());
		System.out.println("nodeno="+nodeno);
		LandPriceVO lvo = new LandPriceVO(); 
		lvo.setNodeno(nodeno);
		boolean successFlag = ldao.landPriceDelete(lvo);  
		//화면출력
		if(successFlag == true) {
			System.out.println(nodeno +"번호를 삭제 하였습니다.");
		}else {
			System.out.println(nodeno +"번호 삭제 실패하였습니다.");
		}
	} 
	//과목수정(update)
	public void updateManager() {
		LandPriceDAO ldao = new LandPriceDAO(); 
		LandPriceVO lvo = new LandPriceVO(); 
		//수정하기 전체출력요청
		ArrayList<LandPriceVO> list = ldao.landPriceSelect();
		if(list.size() != 0) {
			printLessonList(list); 
		}else {
			System.out.println("출력할 데이터가 없습니다.");
			return; 
		}
		//화면으로부터 입력받는다.
		System.out.print("수정할 번호선택>>");
		int nodeno = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 위도입력>>");
		double gpslati= Double.parseDouble((sc.nextLine()).trim());

		System.out.print("수정할 경도입력>>");
		double gpslong= Double.parseDouble((sc.nextLine()).trim());

		System.out.print("수정할 아이디>>");
		String nodeid = (sc.nextLine()).trim();

		System.out.print("수정할 정류소명>>");
		String nodenm = (sc.nextLine()).trim();
		
		lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);  
		boolean successFlag = ldao.landPriceUpdate(lvo);
		
		//화면출력
		if(successFlag == true) {
			System.out.println(nodeno+"과목을 수정 하였습니다.");
		}else {
			System.out.println(nodeno +"과목을 수정 실패 하였습니다.");
		}
	} 	

	//화면출력
	public void printLessonList(ArrayList<LandPriceVO> list) {
		for(LandPriceVO data : list ) {
			System.out.println(data);
		}
	}

}





