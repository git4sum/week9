package Adaptor;

import java.util.ArrayList;
import java.util.List;

//import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// 아래와 같은 자료구조를 구현해보세요.
		Stack s = new Stack(5);
		
		s.push(10);
		s.push(20);

		System.out.println(s.pop());	// 20
		System.out.println(s.pop());	// 10
		
//		s.add(0, 20);	// 스택의 특성이 깨져버립니다. 
	}

}
// Stack: LIFO -> Last In First Out
// 자료구조 내부가 어떻게 구현되었는지는 중요하지 않고 LIFO의 특성만 만족하면 이를 스택이라고 합니다.

// push(10), pop(), isFull(), isEmpty(), ...


// Adaptor pattern(어댑터 패턴)
// 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 패턴.

// 객체 지향 프로그래밍에서 어댑터 패턴을 구현하는 방법
// 1. 상속(X) - 기존 객체의 멤버들도 외부로 노출되므로 객체의 상태가 불안해집니다. 
// 2. 포함(O) - 기존 객체의 멤버들이 외부로 노출되지 않으므로 상속보다 상대적으로 안전합니다.

// 아래의 코드는 포함을 사용한 어댑터 패턴
class Stack {
	// 배열 객체를 상속이 아닌 포함을 시킵니다.
	private ArrayList arr;
	private int size;
	public Stack(int size) { 
		this.arr = new ArrayList<>(size);
		this.size = size; 
	}
	
	public void push(int data) {
		arr.add(data);	// == add(new Integer(data))
	}
	public int pop() {
	//	get(size()-1);
		return (int)arr.remove(arr.size()-1);
	}
	public boolean isEmpty() { return arr.size() == 0; }
	public boolean isFull() { return arr.size() == size; }
}

// 아래의 코드는 상속을 사용한 어댑터 패턴
//class Stack extends ArrayList {
//	private int size;
//	public Stack(int size) { this.size = size; }
//	
//	public void push(int data) {
//		add(data);	// == add(new Integer(data))
//	}
//	public int pop() {
////		get(size()-1);
//		return (int)remove(size()-1);
//	}
//	public boolean isEmpty() { return size() == 0; }
//	public boolean isFull() { return size() == size; }
//}

// 바퀴를 새로 깎아서 쓰지 말고 이전 바퀴를 사용하자! -> 재사용
/*class Stack {
	private int arr[];
	private int top = 0;
	
	public Stack(int size) { arr = new int[size]; }
	
	public void push(int data) { arr[top++] = data; }
	public int pop() { return arr[--top]; }
	
	public boolean isFull() { return top == arr.length; }
	public boolean isEmpty() { return top == 0; }
}*/

/*
class Mystack {
	private int size;
	List<Integer> list;
	public Mystack(int size) {
		this.size = size;
		list = new ArrayList<>(size);
	}
	
	public void push(int value) {
		if(isFull()) System.out.println("Stack is Full"); 
		else list.add(value); 
	}
	public int pop() {
		if(isEmpty()) {
			System.out.println("Stack is Empty");
			return -1;
		}
		return list.remove(list.size()-1);
	}
	public boolean isFull() {
		if(list.size() == size) return true;
		else return false;
	}
	public boolean isEmpty() {
		if(list.size() == 0) return true;
		else return false;
	}
}*/
