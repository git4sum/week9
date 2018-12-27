package AbstractFactory;
/*
public class Test1 {

	public static void main(String[] args) {
		String os = "Win";	// = args[1]
		
		// 이제 각 운영체제 별로 정보창을 출력합니다.
		if(os == "Win") {
			WinTextBox textbox = new WinTextBox();
			WinButton button = new WinButton();
			textbox.draw();
			button.draw();
		}
		else if(os == "Linux") {
			GTXTextBox textbox = new GTXTextBox();
			GTXButton button = new GTXButton();
			textbox.draw();
			button.draw();
		}
		else {
			System.out.println("invalid os option");
		}
	}
}
//--------------------------------------------------------------------------
class WinButton {
	// 각 컴포넌트는 자신이 어떻게 그려져야 할지를 가지고 있습니다.
	public void draw() { System.out.println("Windows Button"); }
}
class WinTextBox {
	public void draw() { System.out.println("Windows GTXdows TextBox"); }
}
//--------------------------------------------------------------------------

// Linux의 GUI 컴포넌트
//--------------------------------------------------------------------------	
class GTXButton {
	public void draw() { System.out.println("GTXdows Button"); }
}
class GTXTextBox {
	public void draw() { System.out.println("GTXdows TextBox");
	}
}
//--------------------------------------------------------------------------
*/

/*
// 이전의 코드는 각 운영체제에 따른 컴포넌트를 생성할 때, 컴포넌트의 이름을 직접적으로 사용하고 있습니다.
// 이는 객체 간의 결합도를 높히게 되므로 유지보수를 어렵게 합니다.
// 이제 객체 생성 코드를 감추기 위해 컴포넌트를 생성하는 공장을 도입하도록 합니다.
public class Test1 {
	public static void main(String[] args) {
		String os = "Win";
		
		TextBox textbox = null;
		Button button = null;
		
		if(os == "Win") {
			WinFactory factory = new WinFactory();
			textbox = factory.createTextBox();
			button = factory.createButton();
			textbox.draw();
			button.draw();
		}
		else if(os == "Linux") {
			GTKFactory factory = new GTKFactory();
			textbox = factory.createTextBox();
			button = factory.createButton();
			textbox.draw();
			button.draw();
		}
		else {
			System.out.println("invalid os option");
		}
	}
}
// 운영 체제별 컴포넌트 생성 공장 클래스를 설계합니다.
class WinFactory {
	// 컴포넌트 생성을 위한 메서드를 도입합니다.
	public Button createButton() { return new WinButton(); }
	public TextBox createTextBox() { return new WinTextBox(); }
}
class GTKFactory {
	public Button createButton() { return new GTKButton(); }
	public TextBox createTextBox() { return new GTKTextBox(); }
}

// 각 운영 체제별 컴포넌트를 하나의 타입으로 처리하기 위해 상속을 도입합니다.
abstract class Button {
	public abstract void draw();
}
abstract class TextBox {
	public abstract void draw();
}

// 각 컴포넌트는 해당 컴포넌트의 부모 클래스를 상속하기로 약속합니다.
class WinButton extends Button {
	public void draw() { System.out.println("Windows Button"); }
}
class WinTextBox extends TextBox {
	public void draw() { System.out.println("Windows TextBox"); }
}

// Linux의 GUI 컴포넌트
class GTKButton extends Button {
	public void draw() { System.out.println("GTKdows Button"); }
}
class GTKTextBox extends TextBox {
	public void draw() { System.out.println("GTKdows TextBox");
	}
}*/


// 이전 코드는 GUI 스타일이 이미 빌드 시점에 결정됩니다. 이 경우, 런타임에 GUI 스타일을 변경할 수 없게 됩니다.
// 이제 런타임에 GUI 스타일을 변경할 수 있도록 인터페이스를 도입합니다.
public class Test1 {
	public static void main(String[] args) {
		String os = "Win";
		
		// 이처럼 공장 클래스로 인터페이스 기반으로 구현하여 결합 응집도를 낮춘 패턴을 추상 팩토리 패턴이라고 합니다.
		Factory factory = null;
		
		if(os == "Win") {
			factory = new WinFactory();
		}
		else if(os == "Linux") {
			factory = new GTKFactory();
		}
		else {
			System.out.println("invalid os option");
		}
		TextBox textbox = factory.createTextBox();
		Button button = factory.createButton();
		textbox.draw();
		button.draw();
	}
}
interface Factory {
	public Button createButton();
	public TextBox createTextBox();
}
class WinFactory implements Factory {
	public Button createButton() { return new WinButton(); }
	public TextBox createTextBox() { return new WinTextBox(); }
}
class GTKFactory implements Factory {
	public Button createButton() { return new GTKButton(); }
	public TextBox createTextBox() { return new GTKTextBox(); }
}
abstract class Button {
	public abstract void draw();
}
abstract class TextBox {
	public abstract void draw();
}
class WinButton extends Button {
	public void draw() { System.out.println("Windows Button"); }
}
class WinTextBox extends TextBox {
	public void draw() { System.out.println("Windows TextBox"); }
}
class GTKButton extends Button {
	public void draw() { System.out.println("GTKdows Button"); }
}
class GTKTextBox extends TextBox {
	public void draw() { System.out.println("GTKdows TextBox"); }
}
