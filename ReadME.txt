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
