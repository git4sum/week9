package Refactoring;

import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
	public static void main(String[] args) {
		new PowerPoint().run();
	}
}


abstract class Shape {
	public abstract void draw();
}
class Rect extends Shape {
	public void draw() { System.out.println("draw rect"); }
}
class Circle extends Shape {
	public void draw() { System.out.println("draw circle"); }
}

// 새로운 도형을 추가할 경우, Shape만 상속하면 됩니다.
class Triangle extends Shape {
	public void draw() { System.out.println("draw triangle"); }
}

// 2. 도형을 생성하는 팩토리 객체를 도입합니다.
class ShapeFactory{
	public static Shape createShape(String cmd) {
		switch(cmd) {
		// 3. 변하는 부분 붙여넣기
		case "1": return new Rect();
		case "2": return new Circle();
		case "3": return new Triangle();
		default: throw new RuntimeException("wrong cmd");
		}
	}
}

class PowerPoint {
	private static Scanner keyboard = new Scanner(System.in);
	ArrayList<Shape> shapes = new ArrayList<>();
	
	public void run() {
		while(true) {
			String cmd = keyboard.nextLine();
			
			if(cmd == "0") {
				for(Shape e:shapes)
					e.draw();
			}
			else {
				// 4. 변하는 부분은 factory 객체로 대체
				shapes.add(ShapeFactory.createShape(cmd));
			}
			// 1. 변하는 것과 변하지 않는 것을 분리한다.
		}
	}
}


/*
// Refactoring: replace type code with polymorphism
abstract class Shape {
	//LSP(리스코프의 치환 법칙): 자식의 공통된 기능은 반드시 부모가 제공해야 합니다.
	public abstract void draw();
}
class Rect extends Shape {
	public void draw() { System.out.println("draw rect"); }
}
class Circle extends Shape {
	public void draw() { System.out.println("draw circle"); }
}

class PowerPoint {
	private static Scanner keyboard = new Scanner(System.in);
	ArrayList<Shape> shapes = new ArrayList<>();
	
	public void run() {
		while(true) {
			String cmd = keyboard.nextLine();
			switch (cmd) {
			case "0":
				for(Shape e : shapes)
					e.draw();	// 다형성(polymorphism)
								// : 하나의 모양이 상황에 따라 다르게 동작하는 개념
				break;
			case "1":
				shapes.add(new Rect());
				break;
			case "2":
				shapes.add(new Circle());
				break;
			}
		}
	}
}*/


/*
// 1. 이제 서로 다른 타입을 동종의 타입으로 처리하도록 부모 클래스를 도입합니다.
class Shape {
	// 3. 부모 타입으로는 자식 객체의 타입을 알 수 없으므로 아래와 같이 타입 코드를 도입하여 자식 타입의 객체를 식별합니다.
	public static enum TypeCode { RECT, CIRCLE }
	public TypeCode type;	// private인데 게터세터귀찮으니까일단퍼블릭
}
// 2. 그리고 모든 도형 개발자는 Shape 클래스를 상속하기로 약속합니다.
class Rect extends Shape {
	// 4. 자식 객체 생성 시, 타입 코드를 설정하기 위해 아래의 생성자를 추가합니다.
	public Rect(TypeCode type) { this.type = type; }
	public void draw() { System.out.println("draw rect"); }
}
class Circle extends Shape {
	public Circle(TypeCode type) { this.type = type; }
	public void draw() { System.out.println("draw circle"); }
}

class PowerPoint {
	private static Scanner keyboard = new Scanner(System.in);
	ArrayList<Shape> shapes = new ArrayList<>();
	
	public void run() {
		while(true) {
			String cmd = keyboard.nextLine();
			switch (cmd) {
			case "0":
				for(Shape e : shapes)
					// 부모 클래스에 객체의 타입이 존재하므로 이를 사용하여 자식 객체의 타입으로 변경하여 호출합니다.
					switch (e.type) {
					case RECT:
						((Rect)e).draw();
						break;
					case CIRCLE:
						((Circle)e).draw();
						break;
					}
				break;
			case "1":
				shapes.add(new Rect(Shape.TypeCode.RECT));
				break;
			case "2":
				shapes.add(new Circle(Shape.TypeCode.CIRCLE));
				break;
			}
		}
	}
}*/

/*
// 일단 프로그램 초기에는 사각형만 그릴 수 있다고 가정합니다.
// 이제 사각형 뿐만 아니라 원도 추가하고 출력할 수 있도록 코드를 변경.

//interface Shape {
//	public void draw();
//}
class Rect implements Shape {
	// 모든 도형은 자신이 화면에 어떻게 그려질지에 대한 기능이 있습니다.
	public void draw() { System.out.println("draw rect"); }
}
//class Circle implements Shape {
//	public void draw() { System.out.println("draw circle"); }
//}
// 도형을 생성 및 출력을 하는 파워포인트 클래스를 설계합니다.
class PowerPoint {
	private static Scanner keyboard = new Scanner(System.in);
	
	// 도형을 저장하는 컨네이터를 추가합니다.
	ArrayList<Rect> shapes = new ArrayList<>();
//	ArrayList<Shape> shapes = new ArrayList<>();
	
	public void run() {
		while(true) {
			
			String cmd = keyboard.nextLine();
			switch (cmd) {
			case "0":	// 도형을 출력
				for(Shape e : shapes)
					e.draw();
				break;
			case "1":	// 사각형을 추가
				shapes.add(new Rect());
				break;
			case "2":	// 원을 추가
				shapes.add(new Circle());
				break;
			}
		}
	}
}
*/
