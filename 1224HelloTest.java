

// http://github.com//Yooii-Studios/Clean-code

public class HelloTest {

	public static void main(String[] args) {
		// 표준 출력
		System.out.println("Hello, world");
	}

}

// OCP(Open Close Principle)
// -> 기능 확장에는 열려 있고(open) 코드 수정에는 닫혀 있는(close) 원리

/*
tightly coupling(강결함) 일 시 OCP 만족 불가능
--> interface 기반으로 loosely coupling 수행
ex) // 첫 번째 보다 두 번째 방법이 더 좋다.  
    Complex fulcrumPoint = new Complex(23.0);  
    Complex fulcrumPoint = Complex.FromRealNumber(23.0);
    // Complex가 Complex1 따위로 바뀔 시 첫 번째 코드는 강한 결합이 되어 있어 수정이 힘듬.
       따라서 가급적 생성자를 직접 쓰지 말고, 생성자는 private로 하고 정적 메소드를 사용.
*/

class Test{
	int a;
	void goo() {
		++a;
	}
	void foo() {
		a = 0;
		goo();	//인자로 전달된 값을 1 증분시키는 코드
		System.out.println(a);
	}
}



