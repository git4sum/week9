package Strategy;

import java.io.IOException;

// 현재 코드는 템플릿 메서드 패턴을 사용하는데 이는 상속을 사용하므로 런타임에 정책을 변경할 수 없으며
// 데이터 공유도 안된다는 단점이 있습니다.
// 이를 해결하기 위해서 정책을 런타임에 변경할 수 있도록 인터페이스를 활용한 포함 관계로 구현합니다.

// Stategy Pattern

public class Test {

	public static void main(String[] args) {
		LineEdit edit = new LineEdit();
		
		edit.setValidator(new Validator() {
			@Override
			public boolean isValid(char ch) {
				return Character.isDigit(ch);
			}
		});
		
		while(true) {
			String text = null;
			try {
				text = edit.getText();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(text);
		}
	}
}

// 정책을 런타임에 변경할 수 있도록 인터페이스를 도입합니다.
interface Validator {
	public boolean isValid(char ch);
}

class LineEdit{
    private StringBuffer text =new StringBuffer();
    private Validator validator = null;
    
    public void setValidator(Validator validator) {
    	this.validator = validator;
    }

    public String getText() throws IOException {
        text.delete(0, text.length());
        while(true) {
            char ch= (char)System.in.read();
            if(ch=='\n')
                break;
            if(validator == null || validator.isValid(ch))
                text.append(ch);
        }
        return text.toString();
    }
}
