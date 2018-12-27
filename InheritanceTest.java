
public class InheritanceTest {

	public static void main(String[] args) {
		Medic medic = new Medic();
		Marine marine = new Marine();
		
//		marine = medic; 	// ERROR, 서로 다른 타입
		Human h1 = medic;	// 부모 타입 = 자식 객체, 업캐스팅(upcasting)
		Human h2 = marine;
		
		// 관련이 없는 타입은 처리할 수 없으므로 타입 안정성이 높아지게 됩니다.
//		Human h3 = new Hydra();	 // ERROR
		
		// 상속을 사용하는 이유
		// 기능적: 코드의 재사용성
		// 디자인: 서로 다른 타입을 동종의 타입으로 처리
	}

}

// Java or C# or C++: 메모리 구조가 같더라도 클래스 이름이 다르면 이는 다른 타입입니다.

// 추상화(abstract): 구체적인 타입으로부터 공통의 속성을 뽑아 내어 새로운 클래스를 생성하는 개념
class Human {} 
class Medic extends Human {}
class Marine extends Human {}
class Hydra {}
