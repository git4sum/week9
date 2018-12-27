
//public class MyTest1 {	
//	//public static int paintColor(int color) {
//	public static int paintColor(Color color) {
//		switch(color) {
//		case Color.RED: System.out.println("RED"); break;
//		case Color.GREEN: System.out.println("GREEN");break;
//		case Color.BLUE: System.out.println("BLUE"); break;
//		default: return -1;
//		}
//		return 0;
//	}
//	
//	public static void main(String[] args) {
//		// 상수를 타입화 한다 하더라도 결국 정수로 처리되므로 타입 안정성을 보장받을 수 없습니다.
//		paintColor(Color.RED);
//		paintColor(Log.ERROR);	// 0: 매직 넘버
//	}
//}
//
//class Color {
////	public static final int RED = 0;
////	public static final int GREEN = 1;
////	public static final int BLUE = 2;
//	
//	// 기본 생성자를 private 영역에 정의하여 사용자가 다른 객체를 생성하지못하도록 합니다.
//	private Color() {}
//	
//	public static final Color RED = new Color();
//	public static final Color GREEN = new Color();
//	public static final Color BLUE = new Color();
//}
//
//class Log {
////	public static final int ERROR = 0;
////	public static final int WARNING = 1;
////	public static final int INFO = 2;
//	public static final Log ERROR = new Log();
//	public static final Log WARNING = new Log();
//	public static final Log INFO = new Log();
//}
/////////////////////////////////////////////////////////////////////////////
//public class MyTest1 {	
//	public static int paintColor(Color color) {
//		switch(color.getValue()) {
//		case Color.COLOR_RED: System.out.println("RED"); break;
//		case Color.COLOR_GREEN: System.out.println("GREEN");break;
//		case Color.COLOR_BLUE: System.out.println("BLUE"); break;
//		default: return -1;
//		}
//		return 0;
//	}
//	
//	public static void main(String[] args) {
//		paintColor(Color.RED);
////		paintColor(Log.ERROR);	// Java 4 까지는 enum이 없어서 이 코드처럼 구현
//	}
//}
//
//class Color {
//	// 객체를 switch 문에서 사용할 수 있도록 상태 코드를 도입합니다.
//	public static final int COLOR_RED = 0;
//	public static final int COLOR_GREEN = 1;
//	public static final int COLOR_BLUE = 2;
//	
//	// 상태를 저장하기 위해 멤버 데이터를 추가합니다.
//	private int value;
//	private Color(int value) { this.value = value; }
//	public int getValue() { return value; }
//	
//	public static final Color RED = new Color(COLOR_RED);
//	public static final Color GREEN = new Color(COLOR_GREEN);
//	public static final Color BLUE = new Color(COLOR_BLUE);
//}
//
//class Log {
//	public static final Log ERROR = new Log();
//	public static final Log WARNING = new Log();
//	public static final Log INFO = new Log();
//}
///////////////////////////////////////////////////////////////////////////
public class MyTest1 {	
	
	public enum Color {
		// 멤버 추가 시, 끝에 세미콜론을 붙여주어야 합니다.
		RED("red"), GREEN("green"), BLUE("blue");	// 이후에 추가 정보를 입력하려면 열거의 끝에 ; 필수
		
		private String color;
		
		// 사용자가 Color에 대한 객체를 임의로 생성할 수 없도록 생성자는 반드시 private으로 정의해야 합니다.
		private Color(String color) { this.color = color; }
		
		@Override
		public String toString() { return color; }
		
	}
	public enum Log {
		ERROR, WARNING, INFO
	}

	public static int paintColor(Color color) {
		switch(color) {
		case RED: System.out.println("RED"); break;
		case GREEN: System.out.println("GREEN");break;
		case BLUE: System.out.println("BLUE"); break;
		default: return -1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		paintColor(Color.RED);
	}
}



