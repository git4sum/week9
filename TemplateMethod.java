package TemplateMethod;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// 윈도우즈에서 윈도우키 + R을 입력해보세요.
		
		// 모든 입력을 그대로 출력
		LineEdit edit = new LineEdit();
		
		// 숫자만 입력 받아 출력
//		DigitLineEdit edit = new DigitLineEdit();
		// 알파벳만 출력
//		AlphaLineEdit edit = new AlphaLineEdit();
		while(true) {
			String text = null;
			try {
				text = edit.getText();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(text);
		}
		
		// 입력을 받아 출력하는 컴포넌트를 구현하되 유지 보수 및 객체 지향적으로 구현
	}
}
/*interface BaseEdit {
	public String getText();
}
class DigitLineEdit implements BaseEdit {
	@Override
	public String getText() {
		String text = new Scanner(System.in).nextLine();
		String result = "";
		for(int i = 0; i < text.length(); i++) {
			if(Character.isDigit(text.charAt(i))) result += text.charAt(i);
		}
		return result;
	}
}
class LineEdit implements BaseEdit {
	@Override
	public String getText() {
		return new Scanner(System.in).nextLine();
	}	
}*/

// 현재 코드는 중복이 발생하고 있습니다. 이를 해결
/*class LineEdit {
	private StringBuffer text = new StringBuffer();
	
	public String getText() throws IOException {
		// 문자열의 기존 내용을 삭제합니다.
		text.delete(0, text.length());
		
		while(true) {
			char ch = (char)System.in.read();
			if (ch == '\n')
				break;
			text.append(ch);
		}
		return text.toString();
	}
}
class DigitLineEdit {
	private StringBuffer text = new StringBuffer();
	
	public String getText() throws IOException {
		// 문자열의 기존 내용을 삭제합니다.
		text.delete(0, text.length());
		
		while(true) {
			char ch = (char)System.in.read();
			if (ch == '\n')
				break;
			
			if(Character.isDigit(ch))
				text.append(ch);
		}
		return text.toString();
	}
}
class AlphaLineEdit {
	private StringBuffer text = new StringBuffer();
	
	public String getText() throws IOException {
		// 문자열의 기존 내용을 삭제합니다.
		text.delete(0, text.length());
		
		while(true) {
			char ch = (char)System.in.read();
			if (ch == '\n')
				break;
			
			if(Character.isAlphabetic(ch))
				text.append(ch);
		}
		return text.toString();
	}
}*/
// 위 코드는 중복이 발생하고 있습니다. 이를 해결


// 메서드 호출 방법
// C/C++: 함수 포인터, 가상 함수(동적 바인딩)
// Java: 메서드 오버라이딩(동적 바인딩)

// 전체 알고리즘은 부모에게 두고 세부 정책은 자식에게 위임합니다.
class LineEdit{
    private StringBuffer text =new StringBuffer();

    //전체 알고리즘은 부모에게 두고 세부 정책은 자식에게 위임
    protected boolean isValid(char ch) {
        return true;
    }
    public String getText() throws IOException {
        //문자열의 기존 내용을 삭제.
        text.delete(0, text.length());
        while(true) {
            char ch= (char)System.in.read();
            if(ch=='\n')
                break;
            if(isValid(ch))
                text.append(ch);
        }
        return text.toString();
    }
}
//템플릿 메서드 패턴이란 알고리즘의 구조를 메서드에 정의하고 하위 클래스에서 알고리즘의 구조
//변경 없이 정책만 변경하는 패턴이다. 즉, 세부 정책을 자신에게 위임하는 패턴이다. 
class DigitLineEdit extends LineEdit{
    protected boolean isValid(char ch) {
        return Character.isDigit(ch);
    }
}
class AlphabetLineEdit extends LineEdit{
    protected boolean isValid(char ch) {
        return Character.isAlphabetic(ch);
    }
}







