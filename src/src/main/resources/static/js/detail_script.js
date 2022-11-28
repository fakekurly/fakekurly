function count(type)  {
    // 결과를 표시할 element
    const resultElement = document.getElementById('result');
    
    // 현재 화면에 표시된 값
    let number = resultElement.innerText;
    let stock = $('#stock').text();
    console.log(stock);
   
    // 더하기 : 재고보다 많게 선택하면 안된다고 알려주기
    if(type === 'plus') {
      if(parseInt(number)-1>=0){
          $('#btn_minus').attr('disabled',false);
          if(parseInt(number)+1>stock){
              alert("재고가 부족합니다.");
          }else{
              number = parseInt(number)+1;
              plusPrice();
          }
      
      }
      else{
          if(parseInt(number)+1>stock){
              alert("재고가 부족합니다.");
          }else{
              number = parseInt(number)+1;
              plusPrice();
          }
      }
          
    }else if(type === 'minus')  { //빼기
      if(parseInt(number)-1<=1){
          $('#btn_minus').attr('disabled',true);
          number = 1;
          minusPrice();	
      }
      else{
              number = parseInt(number) - 1;
              minusPrice();
          }	
    }
    // 결과 출력. 선택한 수량
    resultElement.innerText = number;

console.log(number);
}
  
function gotoCart() {
  location.href = "cartlist.jsp";
}	
$(function(){
    $("#confirm").click(function(){
        gotoCart();
        
        //컨펌 이벤트 처리
    });
    $("#btn_cart").click(function(){        
        $("#popup").css('display','flex').hide().fadeIn();
        //팝업을 flex속성으로 바꿔준 후 hide()로 숨기고 다시 fadeIn()으로 효과
    });
    $("#close").click(function(){
        modalClose(); //모달 닫기 함수 호출
    });
    function modalClose(){
        $("#popup").fadeOut(); //페이드아웃 효과
    }
      
    if($('#stock').text()=='0'){
        //장바구니 담기, 바로주문 버튼 없애고 품절 버튼 나오게
        $('#btn_cart').css('display','none');
        $('#btn_order').css('display','none');
        
        $('#btn_out').css('display','block');
    }else{
        $('#btn_cart').css('display','block');
        $('#btn_order').css('display','block');
        
        $('#btn_out').css('display','none');		  
    }
  });

function plusPrice(){
					
    const priceElement = document.getElementById('price');
    
    let original_price = '<%=price%>' //화면에 표시된 원래 가격
    let now_price = priceElement.innerText; // 계산에 사용될 가격

    let price = now_price;
    //더하기 눌렸을 때 가격
    price = parseInt(now_price)+parseInt(original_price);
    priceElement.innerText = price;

}

function minusPrice(){
    
    const priceElement = document.getElementById('price');
    let original_price = '<%=price%>' //화면에 표시된 원래 가격
    let now_price = priceElement.innerText; // 계산에 사용될 가격

    let price = now_price;
    //빼기 눌렸을 때 가격
    price = parseInt(now_price)-parseInt(original_price);
    priceElement.innerText = price;
}

function sendCartData(){
    var code=0;
    var count=0;
    var price=0;
    
    code=$("#code").html();
    count=$("#result").html();
    price=$("#price").html();
    
    var allData = {"code":code,"count":count,"price":price};
    
    $.ajax({
        url:"cartproc.jsp",
        type:"POST",
        data:allData,
        datatype:"html"
    })
}