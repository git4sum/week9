
public class MyTest {
	
	// 정수를 사용할 경우, 모든 정수에 대하여 호출 가능하므로 안정성이 떨어지게 됩니다.
	// 이를 해결하기 위해 Java 5에서 Enumeration 클래스가 도입됩니다.
	public enum Color {
		RED, GREEN, BLUE
	}
	public enum Log {
		ERROR, WARNING, INFO
	}
//	public static final int RED = 0;
//	public static final int GREEN = 1;
//	public static final int BLUE = 2;
//	
//	public static final int ERROR = 0;
//	public static final int WARNING = 1;
//	public static final int INFO = 2;

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
		// TODO Auto-generated method stub
		paintColor(Color.RED);
//		paintColor(Log.ERROR);	// ERROR.
	}

}
