

### 실습 과제 - API 설계

2024/04/20

<details>
<summary><b> 회원가입</b></summary>
<div markdown="1">

##### End Point 

POST    /users/new 

<br>

##### Path Variable 

X 

<br>

##### Query String 

X 

<br> 

##### Request Body 

```json
{
    "name" : "신희민", 
    "gender" : "W", 
    "birth" : "2000-08-22", 
    "address" : "주소", 
    "address-spec" : "상세 주소", 
    "food-categories" : [category_Id1, id2, id5] , 
    "info-agree" : true , 
    "marketing-agree" : true 

}
```

<br>

##### Request Header 

X 

<br>

</div>
</details> 
    
</br>



<details>
<summary><b> 미션 목록 조회 </b></summary>
<div markdown="1">

##### End Point 

GET   /users/missions

<br>

##### Path Variable 

user-id 

<br>

##### Query String 

"Search-condition" :  "진행중"/ "진행완료"

<br> 

##### Request Body 

X 

<br>

##### Request Header 

"Authentication" : accessToken(String)

<br>

</div>
</details> 
    
</br>



<details>
<summary><b> 미션 성공 요청 </b></summary>
<div markdown="1">

##### End Point 

Post   /users/missions/{mission-id}

<br>



##### Path Variable 

mission-id

<br>



##### Query String 

X

<br> 

##### Request Body 

X 

<br>

##### Request Header 

"Authentication" : accessToken(String)

<br>

</div>
</details> 
    
</br>





<details>
<summary><b> 리뷰 작성 </b></summary>
<div markdown="1">

##### End Point 

Post    /reviews/new

<br>



##### Path Variable 

X 

<br>



##### Query String 

X

<br> 

##### Request Body 

```json
{
	"mission-id" : 2, 
    "title" : "짱", 
    "content" : "완전 맛있어요", 
    "score" : 4, 
    "photo" : ["url1", "url2", "url3"]
}
```

<br>



##### Request Header 

"Authentication" : accessToken(String)

<br>

</div>
</details> 
    
</br>







