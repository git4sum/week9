package Observer;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Button closeBtn = new Button("Close");
//		OnClickListenerImpl l = new OnClickListenerImpl();
//		closeBtn.setOnClickListener(l);
		
		closeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				System.out.println("Window close");
			}
		});
		
		// 버튼이 클릭되었을 경우
		closeBtn.click();
	}

}

// 이전 코드는 이벤트 처리를 하기 위해 상속을 사용했습니다.
// 여기서 버튼의 경우, 라이브러리 안에 있으므로 사용자가 이를 직접 사용할 수 없기 때문입니다.
// 이를 해결하기 위해 이벤트를 버튼이 직접 처리하는 것이 아니라 외부에 알리도록 합니다.

// 외부에 알리기 위해 추상 클래스와 인터페이스를 사용할 수 있는데, 인터페이스 기반으로 구현하는 것이 좋습니다.
interface OnClickListener {
	public void onClick();
}

// 만약 이벤트를 처리하는 핸들러가 재사용을 할 것이라면, 아래와 같이 클래스로 구현해도 된다.
// 하지만 특정 이벤트 처리만 수행할 것이라면 아래와 같이 클래스를 설계하는 것은 일종의 낭비이다.
class OnClickListenerImpl implements OnClickListener {
	@Override
	public void onClick() {
		
	}
}

class Button {
	protected String label;
	private OnClickListener listener = null;
//	private ArrayList<OnClickListener> list = new ArrayList<>();
	
	public Button(String label) { this.label = label; }
	public void setOnClickListener(OnClickListener l) {
		listener = l;
//		list.add(l);
	}
	
	public void click() {
		if(listener != null) listener.onClick();
	}
}

/*
class Button {
	private String label;
	public Button(String label) { this.label = label; }
	
	// 현재 버튼은 이미 라이브러리로 구현되어 있어 수정할 수 없으므로
	// 사용자는 버튼의 콜백 메서드에 처리 코드를 구현할 수 없습니다.
	public void click() {}
}
// 이제 버튼에 대한 콜백을 구현하기 위해 기존 버튼을 상속하여 구현합니다.
class CloseButton extends Button {
	public CloseButton(String label) { super(label); }
	@Override
	public void click() {
		System.out.println("다이얼로그가 닫힙니다.");
	}
}*/

/*public class Main {

	public static void main(String[] args) {
		Button closeBtn = new Button("Close");
		
		// 버튼이 눌릴 경우, 내부적으로 아래의 메서드가 호출됩니다.
		closeBtn.click(); // 이처럼 누군가에 의해 호출을 당하는 개념을 콜백(callback) 이라고 합니다.
	}

}
class Button {
	private String label;
	public Button(String label) { this.label = label; }
	
	public void click() {
		// 이 메서드 안에서 버튼이 클릭되었을 때의 이벤트를 처리하면 됩니다.
		System.out.println("다이얼로그가 닫힙니다.");
	}
}*/



//Observer Pattern(옵서버 패턴)은 객체의 상태 변화를 관찰하는 관찰자들,
//즉 옵저버들의 목록을 객체에 등록하여 상태 변화가 있을 때마다 메서드 등을 통해 객체가
//직접 목록의 각 옵저버에게 통지하도록 하는 디자인 패턴이다.

//옵저버 패턴은 객체의 상태 변화를 관찰하고 이를 처리하기 위한 디자인 패턴입니다.
//이벤트가 발생될 객체에 옵저버 객체들을 등록하고 이벤트가 발생되면 발생된 객체는
//등록된 옵저버들의 메서드를 호출하여 옵저버에게 알려줍니다.



// 디자인 패턴: 반복적으로 발생하는 문제들을 설명하고 이에 대한 해결 방안을 설명하는 학문
// 리팩토링: 프로그램의 동작을 변경하지 않고 내부 구조를 변경하는 유지 보수의 개념이다.

// 디자인 패턴은 궁극적인 목표이고 리팩토링은 이에 대한 방법론이다.
// 디자인 패턴은 숲을 보는 것이고 리팩토링은 나무를 보는 것이다.














