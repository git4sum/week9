
public class CalcTest {
	
//	public static int calculate(Operator op, int n1, int n2) {
//		int result = 0;
//		switch(op) {
//		case ADD: result = n1 + n2; break;
//		case SUB: result = n1 - n2; break;
//		case MUL: result = n1 * n2; break;
//		case DIV: result = n1 / n2; break;
//		default: throw new RuntimeException("invalid operator");
//		}
//		return result;
//	}

	public static void main(String[] args) {
		System.out.println(Operator.ADD.calc(1, 1));
	}

}
// 현재 코드는 열거의 상태와 그 행위가 나뉘어져 있어 유지보수하기 어렵습니다. 이를 해결-> 하나의 클래스로 만들자
//enum Operator {	ADD, SUB, MUL, DIV }
enum Operator {	
	ADD{ @Override
	public int calc(int n1, int n2) {
		return n1 + n2;
	}
	}, SUB{
		@Override
		public int calc(int n1, int n2) {
			return n1 - n2;
		}
	}, MUL{
		@Override
		public int calc(int n1, int n2) {
			return n1 * n2;
		}
	}, DIV{
		@Override
		public int calc(int n1, int n2) {
			return n1 / n2;
		}
	};
	
	public abstract int calc(int n1, int n2);
	
}
