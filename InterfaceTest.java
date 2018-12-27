
public class InterfaceTest {

	public static void main(String[] args) {
		Person p = new Person();
		
		Anycall a = new Anycall();
		p.usePhone(a, "010-0000-0000");
		
		Cyon c = new Cyon();
		p.usePhone(c, "010-1111-1111");
		
		Sky s = new Sky();
		p.usePhone(s, "010-1111-1111");
	}
}

// 핸드폰과 사람 사이의 규약을 설정합니다. 이제 모든 핸드폰 개발자 또는 설계자는 Phone을 상속하기로 약속합니다.
/*abstract class Phone {
	public abstract void send(String n);
}*/
// 객체지향 프로그래밍에서 자식에 어떠한 기능을 물려주는 용도가 아닌
// 어떤 기능의 구현을 강제하기 위해 사용되는 클래스를 인터페이스라고 합니다.
// 인터페이스는 결국 객체와 객체 사이의 통신을 하기 위한 규약!
interface Phone {
	void send(String n);
}

// Person 시스템이 제공하는 기능이라고 생각합니다.
// 이제 모든 핸드폰 개발자 또는 설계자는 Phone을 상속하기로 약속합니다.
class Anycall implements Phone {
	public void send(String n) {
		System.out.printf("Calling %s with Anycall...\n", n);
	}
}
class Cyon implements Phone {
//	public void call(String n) {
	public void send(String n) {
		System.out.printf("Calling %s with Cyon...\n", n);
	}
}
class Sky implements Phone {
	public void send(String n) {
		System.out.printf("Calling %s with Sky...\n", n);
	}
}

// Person은 시스템이라고 생각합니다.
// Person 클래스는 객체 지향의 5대 원칙 중 하나인 OCP(Open Close Principle)를 만족하지 않습니다.
// OCP를 만족하지 않는 이유는 Person 클래스가 핸드폰의 이름을 직접적으로 사용하기 때문입니다.
// 이 때, 사람과 핸드폰은 강하게 결합되어 있다고 하여 강결합(tightly coupling)이라고 합니다.
// 이 문제를 해결하려면 사람과 핸드폰이 느슨하게 결합되어 있어야 하는데 이를 약결합(loosely coupling)이라고 합니다.

// Person <----------------------> Cyon or Anycall (X)
// Person <---> Object:Phone <---> Cyon or Anycall (O)
//                       ^---간접층의 원리: 어떠한 문제에 직면했을 경우, 새로운 객체를 도입하면 문제가 해결된다는 원리
class Person{
//	public void usePhone(Anycall p, String n) { p.send(n); }
//	public void usePhone(Cyon p, String n) { p.call(n); }
	public void usePhone(Phone p, String n) { p.send(n); }
}


// 상속: 코드의 재사용성, 서로 다른 타입을 동종의 타입으로 처리

// 부모 클래스: 자신의 기능을 물려주기 위해서 사용
// 추상 클래스: 자신의 기능을 물려주기 위함 + 특정 기능 구현을 강제하기 위해 사용
// 인터페이스: 특정 기능 구현을 강제하기 위해서만 사용


