package com.itwillbs.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GoodsAddAction_excute() 호출");
		// 로그인 세션 제어 (생략)
		request.setCharacterEncoding("UTF-8");
		// 파일 업로드
		// upload 폴더 생성
		// - 파일 저장경로
		// String realPath = request.getRealPath("/upload");
		// 좀더 정확하게 주소를 불러오기 위해 밑에처럼 사용한다
		// 이렇게 사용하는것을 권장한다
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/upload");
		System.out.println(" M : "+realPath);
		
		// - 퍼일 업로드 크기 제한
		int maxSize = 100 * 1024 * 1024; // 10m
		
		// 파일 업로드 처리 - 객체 생성 
		MultipartRequest multi = 
				new MultipartRequest(
						request, 
						realPath, 
						maxSize, 
						"UTF-8", 
						new DefaultFileRenamePolicy() // 파일 이름 중복 막아주는 객체
						);
		System.out.println(" M : 파일 업로드 왈료!");
		
		// 상품 데이블 생성(itwill_goods)
		// 전달된 상품 정보를 저장 (DTO)객체에 저장
		GoodsDTO gdto = new GoodsDTO();
		
		gdto.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gdto.setBest(0);// 인기상품(1),일반(0)
		gdto.setCategory(multi.getParameter("category"));
		gdto.setColor(multi.getParameter("color"));
		gdto.setContent(multi.getParameter("content"));
		//gdto.setDate(date)
		
		String image =
				multi.getFilesystemName("file1")+","
				+multi.getFilesystemName("file2")+","
				+multi.getFilesystemName("file3")+","
				+multi.getFilesystemName("file4");
		
				System.out.println(" M : "+image);
		
		gdto.setImage(image);
		gdto.setName(multi.getParameter("name"));
		//gdto.setNum();
		gdto.setPrice(Integer.parseInt(multi.getParameter("price")));
		gdto.setSize(multi.getParameter("size"));
		
		System.out.println(" M : 전달된 정보 저장완료!");
		System.out.println(" M : "+gdto);
		
		// DB에 정보 저장(DAO) - insertGoods(DTO)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.insertGoods(gdto);
		
		// 페이지 이동
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}



}
