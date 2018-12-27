package FactoryMethod;

public class Test {

	public static void main(String[] args) {
		Win10Dialog dlg = new Win10Dialog("Hello, world");
		dlg.show();
		
		Win7Dialog dlg2 = new Win7Dialog("GGGGGGG");
		dlg2.show();
	}
}
// 이전 코드는 버전에 따른 타입 코드를 사용했습니다. 코드에 타입 코드가 도입이 되면 반드시 분기문이 추가되는데
// 이러한 코드가 존재할경우, 새로운 기능이 도입되면 코드 수정이 불가피합니다.
// 따라서 새로운 코드가 추가된다 하더라도 기존 코드의 수정 없이 또는 최소로 수정될 수 있도록 코드를 변경.

// 버튼 객체 생성은 부모 클래스가 어떤 타입의 버튼을 생성해야 하는지 모르므로 이를 자식에게 위임한다.
abstract class Dialog {
	private String message;
	private Button button;
	
	public Dialog(String message) {
		this.message = message;
		this.button = createButton("OK");
	}
	// 이제 버튼 생성을 부모가 아닌 자식에게 위임합니다.
	// 이처럼 객체 생성을 부모가 아닌 자식 클래스에게 위임하는 패턴을 팩토리 메서드 패턴이라고 합니다.
	protected abstract Button createButton(String label);
	public void show() {
		System.out.println(message);
		button.draw();
	}
}
// 서로 다른 타입을 동종의 타입으로 처리하기 위해 부모 클래스를 도입(상속)합니다.
abstract class Button {
	protected String label;
	public Button(String label) { this.label = label; }
	
	// 자식의 공통의 기능은 반드시 부모가 제공해야 합니다. (LSP)
	public abstract void draw();
}
class Win10Button extends Button {
	public Win10Button(String label) { super(label); }
	@Override
	public void draw() {
		System.out.printf("< %s >\n", label);
	}
}
class Win10Dialog extends Dialog { // extends Window {
	public Win10Dialog(String message) { super(message); }
	@Override
	protected Button createButton(String label) {
		return new Win10Button(label);
	}
}
class Win7Button extends Button {
	public Win7Button(String label) { super(label); }
	@Override
	public void draw() {
		System.out.printf("[ %s ]\n", label);
	}
}
class Win7Dialog extends Dialog { // extends Window {
	public Win7Dialog(String message) { super(message); }
	@Override
	protected Button createButton(String label) {
		return new Win7Button(label);
	}
}

/*
public class Test {

	public static void main(String[] args) {
		// 아래의 라이브러리는 코드의 중복 등 여러 가지 문제점들이 있습니다. 이를 개선
		Win10Dialog dlg = new Win10Dialog("Hello, world");
		dlg.show();
		
		Win7Dialog dlg2 = new Win7Dialog("GGGGGGG");
		dlg2.show();
	}
}
// 각 버전별 GUI 스타일이 다르다 하더라도 다이얼로그 클래스에는 공통의 속성이 존재합니다.
// 코드의 중복이 발생하므로  공통의 속성을 뽑아내어 부모 클래스를 설계합니다.
class Dialog {
	// 모든 다이얼로그에는 버튼이 존재하지만 각 스타일에 대한 버튼은 아무런 관계가 없으므로 이를 부모 클래스로 이동시킬 수 없습니다.
	// 따라서 각 스타일에 따른 타입 코드를 도입하도록 합니다.
	public static enum Style { WIN7, WIN10 }
	
	private Style style;
	private String message;
	
	public Dialog(String message, Style style) {
		this.message = message;
		this.style = style;
	}
	
	public void show() {
		System.out.println(message);
		if(style == Style.WIN7)
			new Win7Button("OK").draw();
		else if(style == Style.WIN10)
			new Win10Button("OK").draw();
	}
}
// 이제 각 버전 별 다이얼로그를 구현하는 설계자는 Dialog 클래스를 상속하기로 약속합니다.
class Win10Button {
	private String label;
	public Win10Button(String label) { this.label = label; }
	public void draw() {
		System.out.printf("< %s >\n", label);
	}
}
class Win10Dialog extends Dialog {
	public Win10Dialog (String message) {
		super("hello, world", Dialog.Style.WIN10);
	}
}
class Win7Button {
	private String label;
	public Win7Button(String label) { this.label = label; }
	public void draw() {
		System.out.printf("[ %s ]\n", label);
	}
}
class Win7Dialog extends Dialog {
	public Win7Dialog (String message) {
		super("hello, world", Dialog.Style.WIN7);
	}
}*/

/* Mycode
abstract class BaseButton {
	private String label;
	public BaseButton(String label) { this.label = label; }
	
	public String getLabel() { return label; }
	public abstract void draw();
}
class BaseDialog {
	private String message;
	protected BaseButton button;

	public BaseDialog (String message) {
		this.message = message;
	}
	public void show() {
		System.out.println(message);
		button.draw();
	}
}

class Win10Button extends BaseButton {
	public Win10Button(String label) { super(label); }
	@Override
	public void draw() {
		System.out.printf("< %s >\n", getLabel());
	}
}
class Win10Dialog extends BaseDialog { // extends Window {
	public Win10Dialog(String message) { 
		super(message); 
		button = new Win10Button("OK");
	}
}

class Win7Button extends BaseButton {
	public Win7Button(String label) { super(label); }
	@Override
	public void draw() {
		System.out.printf("[ %s ]\n", getLabel());
	}
}
class Win7Dialog extends BaseDialog { // extends Window {
	public Win7Dialog(String message) { 
		super(message);
		button = new Win7Button("OK"); 
	}
}*///MyCode

/*
public class Test {

	public static void main(String[] args) {
		// 아래의 라이브러리는 코드의 중복 등 여러 가지 문제점들이 있습니다. 이를 개선
		Win10Dialog dlg = new Win10Dialog("Hello, world");
		dlg.show();
	}
}
//Win10 Style--------------------------------------------------
class Win10Button {
	private String label;
	public Win10Button(String label) { this.label = label; }
	
	public void draw() {
		System.out.printf("< %s >\n", label);
	}
}
class Win10Dialog { // extends Window {
	private String message;
	private Win10Button button;
	
	public Win10Dialog (String message) {
		this.message = message;
		this.button = new Win10Button("OK");
	}
	public void show() {
		System.out.println(message);
		button.draw();
	}
}
//-------------------------------------------------------------
// Win7 Style---------------------------------------------------
class Win7Button {
	private String label;
	public Win7Button(String label) { this.label = label; }
	
	public void draw() {
		System.out.printf("[ %s ]\n", label);
	}
}
class Win7Dialog { // extends Window {
	private String message;
	private Win7Button button;
	
	public Win7Dialog (String message) {
		this.message = message;
		this.button = new Win7Button("OK");
	}
	public void show() {
		System.out.println(message);
		button.draw();
	}
}
//-------------------------------------------------------------
*/

//public class Test {
//
//	public static void main(String[] args) {
//		Win7Dialog dlg = new Win7Dialog("Hello, world");
//		dlg.show();
//	}
//}
//
//class Win7Button {
//	private String label;
//	public Win7Button(String label) { this.label = label; }
//	
//	// GUI의 컴포넌트들은 자신이 어떻게 그려져야 할지에 대한 정보를 가지고 있습니다.
//	public void draw() {
//		System.out.printf("[ %s ]\n", label);
//	}
//}
//
//// 이제 버튼을 붙이기 위한 다이얼로그 창을 설계합니다.
//class Win7Dialog { // extends Window {
//	private String message;
//	private Win7Button button;
//	
//	public Win7Dialog (String message) {
//		this.message = message;
//		this.button = new Win7Button("OK");
//	}
//	
//	// 대부분의 GUI 라이브러리들은 윈도우를 화면에 보여주기 위한 별도의 인터페이스를 가지고 있습니다.
//	public void show() {
//		System.out.println(message);
//		button.draw();
//	}
//}
