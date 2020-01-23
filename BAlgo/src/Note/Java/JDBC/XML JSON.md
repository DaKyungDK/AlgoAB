# XML

###### HTML의 단점

- 한정된 태그사용
- 태그의 의미전달 못함
- 문서의 계층 구조를 표현 못함
- 정보의 재사용이 어려움



#### XML

서식이 아니라 구조를 설명(세미 데이터베이스)

XML은 내용의 의미를 담고 있는 태그를 사용자가 정의한다.

##### **XML규칙**

- Case Sensitive : 대소문자 가림

- Closing Tags : `여는태그가` 있다면, 닫는 태그도 있어야 함.



```xml
<!--start tag-->
<name>
<!--end tag-->
</name>
<!--empty tag-->
<name/>
```



#### XML문서 타입 선언

```xml
<?xml version="1.0" encoding="euc-kr" standalone="yes"?>
<!--?xml부터, version까지는 꼭 선언해 줘야 함.-->
<!--인코딩은 ISO8859-1이 기본 설정-->
<!--standalone = 구조를 혼자서 체크할 수 있는가? 디폴트는 no-->
```



#### ASCII

- 0~9, 특수기호,  a~z, A~Z 등 128개의 문자를 0~127로 코드화.

  

#### ISO-8859-1

- ASCII를 확장
- 영어권에서 사용하는 특수기호를 추가하여 1Byte로 표현한 코드.



##### XML태그 규칙

- 태그명을 숫자로 시작하면 안됨
- 태그명 사이에 공백이 있으면 안됨
- <빈태그/>에서 /다음에 공백이 있으면 안됨



XML은 주석도 정보다. -> 원한다면 정보도 가져올 수 있음.



##### 노테이션 : 외부프로그램을 사용할 수 있는 방법 (지금은 안 씀. 그냥 이런게 있다 하고 알면 됨.)



#### 태그 바디에 사용할 수 없는 특수 기호

- &, ', ", < , > 등은 쓸 수 없음. (태그 바디에 사용할 수 없는 특수 기호)
  - & :  &emps;
  - ' : &apos';
  - " : &qout;
  - < : & lt;
  - '> : & gt

#### CDATA 세션

- 모든 텍스트가 마크업이 아닌 문자 데이터로 해석되기 원할 때 사용

- ```xml
  <comparison>
  	<less><![CDATA[2<3]]></less>
      <greater><![CDATA[4>2]]></greater>
  </comparison>
  ```

- 얘는 파싱하지마! 하고 태그바디에 사용할 수 없는 특수기호들을 모두 있는 그대로 써줘 하는것.



##### XML예제

```xml
<!--오류남-->
<address>연수구 송도동 <4동</address>

<!--오류 안남-->
<address>연수구 송도동&lt;4동&gt;</address>
    
<!--한번에 하고싶을 때.-->
<address>연수구 송도동 <![CDATA[<4동>]]> </address>
```



##### Namespace가 필요한 이유

namespace 형식

```xml
<!--url은 유효하지 않아도 됨-->
xmlns:프리픽스="구조(dtd,schema)url"
<프리픽스 : 태그명></프리픽스 : 태그명>
```

```xml
<!--기본 namespace-->
xmlns="구조(dtd,schema) url" <!--프리픽스 필요 없음.>
<태그명></태그명>
```



###### Prefix 사용규칙

- 문자나 '_'로 시작되며 문자, '.', '-', '_'를 포함하는 문자열이 올 수 있다.(한글도 됨)





# SAX Parser

XML 프로세서들마다 API가 달라서 발생하는 개발의 어려움을 해결하기 위해

DOM : modify, replace, add, delete 가능.

![1565057138037](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565057138037.png)



```java
public class SAXHandler extends DefaultHandler {
	/*
	 * xml을 parsing하기 시작할 때 한 번 호출하는 메서드
	 */
	public void startDocument() throws SAXException {
//		super.startDocument(); 전혀 필요없는 코드.
		System.out.println("-----start document------");
	}
	/*
	 * start tag를 parsing할 때 마다 호출되는 메서드
	 * @param uri 			xmln의 URI, XML parser에 따라 전달되지 않는 경우가 있음.
	 * @param localName		tag name
	 * @param qname			prefix를 포함한 tag name
	 * @param attributes	태그에 선언된 속성 정보
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		//super.startElement(uri, localName, qName, attributes); (전혀 필요 없는 코드.)
	}
}
```

..나머지 메서드들은 javaSE에 있음.

#### DefaultHandler메서드

- startDocument() : XML을 parsing하기 시작할 때 한 번 호출되는 메서드

- startElement() : start tag를 parsing할 때 마다 호출되는 메서드

- characters() : 태그 데이터의 문자를 parsing할 때 마다 호출됨

- endElement() : end tag를 parsing할 때 마다 호출됨

- endDocument() : XML parsing을 종료할 때 한 번 호출됨.

  @param uri : xmln의 URI, XML parser에 따라 전달되지 않는 경우가 있음.
  @param localName : tag name
  @param qname : prefix를 포함한 tag name
  @param attributes : 태그에 선언된 속성 정보

attributes 받아오기

```java
if(attributes!=null) {
			int len = attributes.getLength();
			for (int i = 0; i < len; i++) {
				System.out.println(attributes.getLocalName(i)+" : "+attributes.getValue(i));
			}
		}
```

- getLocalName(int index) : 속성 이름 추출
			- getValue(int index) : 속성 값 추출



속성이름은 startElement로 알 수 있지만

endElement로 인하여 태그바디 내용을 알 수 있다

-> 내용을 

characters메서드를 통하여 임시 저장.



# DOM Parser

##### DOM interface

org.w3c.dom.Node

org.w3c.dom.Document : xml

org.w3c.dom.Element : tag

org.w3c.dom.NodeList : 특정 태그의 밑에 있는 자식 태그들을 한꺼번에 받아오기.

![1565070757456](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565070757456.png)

위 표에서 Element, Attr, Text 꼭 알아둘것.



getTextContent() : body가 Text라면, 내용을 얻어올 수 있음.



getDocumentElement() : 루트부터 가져옴.



DOM Parsing -> 저

getElementByTagName(elementId) : elementId와 같은 태그이름을 가진 태그들을 모두 가져옴.->NodeList로 리턴.

getElementsById(elementId) : elementsId와 같은 태그 이름을 가진 태귿르을 모두 가져옴.



# JSON

```json
<!--객체 한개-->
{ 속성명 : 값

,  속성명 : 값

, ...

}
 
<!--배열-->
 [{속성명 : 값
, ...
, ...}
,{속성명:값
 , ...
 , ...}
 ]
```

```json
{name:"ssafy"
,age:"2"
,address:"강남구"}
```



REST API : JackSON, bind, GSON .. -> 나중에 웹에서 배움.

우리는 GSON 활용하도록 함

GSON 다운받으려면, 구글에 maven repository(자바에서 사용하는 모든 라이브러리 존재)검색



##### Object -> GSON

```java
Customer cust = new Customer("김메이", 2, "리빙박스");
Gson gson = new Gson();
//String toJSon(Object o) : 인자에 해당하는 객체를 JSON데이터로 변경하는 기능
String json = gson.toJson(cust);
System.out.println(json);

//배열도 됨.
Customer[] custs = {
    cust,new Customer("김지승",24,"연수구")
};
System.out.println(gson.toJson(custs));
```



##### JSON -> Object

```java
String json= "{'name':'김메이','age':2,'address':'리빙박스'}";
Gson gson = new Gson();
//				class를 받아오는방법은 아래 참고 ↓
Customer cust = gson.fromJson(json, Customer.class);
System.out.println(cust);

//배열도 됨.
String json2 = "[{\"name\":\"김메이\",\"age\":2,\"address\":\"리빙박스\"},{\"name\":\"김지승\",\"age\":24,\"address\":\"연수구\"}]";
Gson gson2 = new Gson();
Customer[] custs = gson2.fromJson(json2, Customer[].class);
for (Customer customer : custs) {
    System.out.println(customer);
}
```



##### Class 정보를 추출하는 방법

1. 객체 생성 후 객체를 통해 추출

   ```java
    Customer c = new Customer();
    c.getClass();클래스명.class
   Customer.class
   ```

2. 클래스명.class

   ```java
   Customer.class
   ```



