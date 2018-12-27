package Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
	
		// 메뉴에 메뉴아이템을 추가합니다.
		Menu file = new Menu("FILE");  
		file.add(new MenuItem("Open"));
		file.add(new MenuItem("Close"));
		file.add(new MenuItem("Exit"));
		
		Menu edit = new Menu("EDIT");
		edit.add(new MenuItem("Copy"));
		edit.add(new MenuItem("Cut"));
		edit.add(new MenuItem("Paste"));
		
		Menu help = new Menu("HELP");
		help.add(new MenuItem("About"));
		help.add(new MenuItem("Search"));
		help.add(new MenuItem("Tip"));
		
		// 각 메뉴를 메뉴 바에 붙입니다.
		Menu bar = new Menu("BAR");
		bar.add(file);
		bar.add(edit);
		bar.add(help);
		
		// 메뉴가 클릭되었다고 가정합니다.
		bar.perform();
		
		// [FILE Menu]
		// 1. Open
		// 2. Close
		// 3. Exit
		// -----------
		// select: 1
		// Open이 선택됨!
	}
}

// 메뉴는 자기 자신과 메뉴 아이템을 포함할 수 있어야 하므로
// 메뉴와 메뉴 아이템을 하나의 타입으로 처리하기 위해 상속을 사용합니다.
// 이러한 디자인 패턴을 composite pattern 이라고 합니다.
abstract class BaseItem {
	private String title;
	public BaseItem(String title) { this.title = title; }
	public String getTitle() { return title; }
	
	// 자식의 공통의 기능은 반드시 부모가 제공해야 합니다.
	// LSP(Liskov Substitution Principle): 리스코프의 치환 원식
	// 객체 지향의 5대 원칙 중 하나입니다.
	// 이 때, 자식 클래스가 아래의 메서드를 반드시 구현하도록 추상 메서드로 선언합니다.
	public abstract void perform();
}

// 메뉴는 메뉴 아이템을 포함합니다.
class MenuItem extends BaseItem {
	// 메뉴 아이템도 자신의 이름이 있습니다.
//	private String title;
//	public String getTitle() { return title; }
	
	// 이제 메뉴와 메뉴아이템은 Item을 상속하기로 약속합니다.
	public MenuItem(String title) { super(title); }
	
	// 메뉴 아이템은 자신이 클릭되었을 때의 이벤트를 처리해야 하므로 이를 수행하기 위한 핸들러를 가지고 있습니다.
	public void perform() {
		System.out.printf("%s이(가) 선택됨!\n", getTitle());
	}
}

class Menu extends BaseItem {
	// 메뉴는 자신의 고유한 이름이 있습니다.
//	private String title;
	private List<BaseItem> list = new ArrayList<>();
	public Menu(String title) { super(title); }
	
//	public String getTitle() { return title; }
	public void add(BaseItem item) {
		list.add(item);
	}
	
	// 메뉴는 자신이 클릭되었을 때, 자신이 가지고 있는 항목들을 출력합니다.
	public void perform() {
		while(true) {
			Menu.clear();
			System.out.printf("[%s Menu]\n", getTitle());
			for(int i = 0; i < list.size(); i++) {
				System.out.printf("%d. %s\n", i + 1, list.get(i).getTitle());
			}
			System.out.println(" -----------");
			System.out.printf("input(0. up): ");
			
			int num = Menu.getInt();
			
			// 만약 0을 입력하면 루프를 빠져나와 종료합니다.
			if (num == 0) break;
			
			// 번호를 잘못 입력했을 경우, 다시 입력을 받는다.
			if(num < 1 || num > list.size()) continue;
			
			// 선택한 항목에 대하여 일을 수행한다.
			list.get(num - 1).perform();
			Menu.pause();
		}
	}
	
	/*ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	public void perform() {
		// 자신이 가지고 있는 항목들을 출력합니다.
		System.out.printf("[%s Menu]\n", title);
		for(int i = 0; i < menuList.size(); i++) {
			menuList.get(i).perform();
		}
		System.out.println(" -----------");
		
		while(true) {
			System.out.printf("select: ");
			Scanner s = new Scanner(System.in);
			int n = s.nextInt();
			if (n==-1) break;
			else if(n > menuList.size() || n == 0) System.out.println("invalid input");
			else menuList.get(n-1).perform();
		}
	}*/ //mycode

	// 아래의 메서드는 출력 코드를 간단하게 하기 위해 사용한 유틸리티 메서드입니다.
	// --------------------------------------------------------------
	// 이클립스의 콘솔 창은 표준 출력 결과를 리다이렉션하여표시하므로 출력 창을 지울 수 업습니다. 이를 위해  clear 메서드를 추가합니다.
	public static void clear() {
		for(int i=0; i<30; i++) System.out.println();
	}
	// 표준 입력을 처리하기 위해 아래의 메서드를 추가합니다.
	public static int getInt() {
		return new Scanner(System.in).nextInt();
	}
	public static void pause() {
		try { 
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}





