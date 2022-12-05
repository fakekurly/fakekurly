11/28 11:42
	* 회원가입: /member/signup
	* 로그인: /member/login
		- 비밀번호 암호화가 적용되어 있기 때문에 회원가입으로 계정 생성 후 테스트 할 것.
		- 로그인에 성공하면 /userManage/welcome 경로로 이동 (AuthSucessHandler.java)
		- /userManage/welcome 페이지는 현재 뷰가 없고 Controller에서 userid, username만 세션에 등록하는 POST 함수만 있음
		- 나중에 페이지를 추가하거나 경로를 바꿀 것.
		- html에서 세션 가져오기: <태그 속성="${session.userid}"> [[${session.username}]]님 </태그>
	* 기존 board 코드를 그대로 가져왔기 때문에 html 페이지를 수정해주어야 함.

jungin-2 : package : com.kurly 
	   product/productdetail 수정 (상품상세)
	   cart/cartlist (장바구니 화면)
	   CartVO, CartController, CartController, CartVO, CartMapper, CartService, CartServiceImpl 추가
	   장바구니 기능 구현 완료
	   
11/30 14:09
	* 회원가입, 로그인, 아이디찾기, 임시비밀번호발급 디자인 변경
	* 아이디 찾기 시 userid 세션이 생성되기 때문에 헤더에서 로그인 유무를 판단할 때 userid가 이를 인식하는 문제가 있어 MemberController.java의 postSearchID(), getFoundIDView()함수에서 세션 userid의 이름을 search_userid로 변경하였음.
	* 변경/추가된 파일:
		- com.kurly.controller: MemberController.java
		- src/main/resources/templates/member:
			- IDView.html
			- login.html
			- searchID.html
			- searchPassword.html
			- signup.html
			- tempPWView.html
		- src/main/resources/static/css:
			- searchForm.css
			- searchView.css
			- signup.css
	* 삭제된 파일:
		- src/main/resources/templates/member: loginCheck.html (필요없으니 삭제)

------------------------------
11/30 18:45
	* 회원정보 보기/변경(비밀번호만 수정 가능) 추가, 일부 디자인 변경
	* 회원탈퇴의 경우 DB 내에 종속된 다른 컬럼의 영향을 받아 작동하지 않음. 장바구니, 좋아요 등 board에서 추가된 다른 테이블 관련 쿼리를 추가하면 될 것으로 보임.
	* 변경된 파일:
		- com.kurly.controller: MemberController.java
		- com.kurly.service: MemberService.java, MemberServiceImpl.java
		- src/main/resources/mappers:
			- MemberMappaer.xml
		- src/main/resources/templates/member:
			- signup.html
		- src/main/resources/templates/userManage:
			- memberInfo.html
		- src/main/resources/static/css:
			- modifyInfo.css
			- signup.css
	* 삭제된 파일:
		- src/main/resources/templates/userManage:
			- memberInfoDelete.html
			- modifyMemberInfo.html
			- modifyPassword.html
			- viewMemberInfo.html
--------------------------------------------------------------
12/02 jungin-2
	* 장바구니->주문, 주문->주문내역 구현 완료
	*변경된 파일 :
		- com.kurly.controller : OrderController.java
		- com.kurly.dto : OrderVO
		- com.kurly.mapper : OrderMapper
		- com.kurly.service : OrderService, OrderServiceImpl
		- src/main/resources/mappers: orderMapper.xml
		- src/main/resources/templates/product : productdetail.html
		- src/main/resources/templates/cart : cartlist.html
		- src/main/resources/templates/order : order.html, orderlist.html
		
	***db tbl_order 변경 필요***
	tbl_order : state, order_date nullable 'yes'
		    order_date 기본값 : TO_CHAR(CURRENT_TIMESTAMP,'YYYY-MM-DD-HH24MI')


-------------------------------------------
12/05 20:15
	* 메인 페이지 추가
	* 변경/추가된 파일:
		- com.kurly: WebSecurityConfig.java
		- com.kurly.controller: ProductController.java
		- com.kurly.mapper: ProductMapper.java
		- com.kurly.service: ProductService.java, ProductServiceImpl.java
		- src/main/resources/mappers: ProductMapper.xml
		- src/main/resources/static/css/main.css
		- src/main/resources/templates/main.html
	* Product 관련해서 함수가 1개만 추가되었음

-------------------------------------------
12/06 03:41 hmj
* 카테고리 상품/신상품 페이지 추가
  script 안에서 오류 뜨는건 javascript 내 타임리프 인식 문제 때문입니다. 코드 돌아가는 것에는 문제 없습니다.
- productVO에서 category 부분이 없는 것을 확인하여 아무도 category 부분은 안쓰신 것 같아 db내 category를 약간 수정했습니다. 

	tbl_category 생성
	tbl_product 수정  → 변경내용 확인할 수 있게 테이블 생성 및 db삽입 sql 올림.

	controller: ProductController.java  → 내용 추가

	dto: BrandVO.java/ CategoryListVO.java/ CategoryVO.java → 추가
	     ProductVo.java → 내용 추가

	mapper: ProductMapper.java → 내용 추가

	service: ProductService.java/ ProductServiceImpl.java → 내용 추가

	resources/mappers : productMapper.xml → 내용 추가

	resource/templates/collections 폴더 추가
		category_product.html / newProduct.html → 추가
		

