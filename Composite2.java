package Composite;

import java.nio.file.DirectoryIteratorException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		// 디렉터리와 파일을 저장하는 구조를 설계하고 디렉터리 안의 파일에 대하여 총 크기를 구하는 프로그램을 구현
		Directory root = new Directory("\\");
		root.add(new File("a.txt", 10));
		
		Directory dir1 = new Directory("A");
		dir1.add(new File("a.txt", 10));
		dir1.add(new File("b.txt", 20));
		dir1.add(new File("c.txt", 30));
		
		Directory dir2 = new Directory("B");
		dir2.add(new File("a.txt", 10));
		dir2.add(new File("b.txt", 10));
		
		root.add(dir1);
		root.add(dir2);
		
		System.out.println(dir1.getSize());	// 60
		System.out.println(dir2.getSize());	// 20
		System.out.println(root.getSize());	// 90
	}

}
/*class Base {
	private String name;
	protected int size;
	
	public Base(String name, int size){ this.name = name; this.size += size; }
	public int getSize() { return size; }

}
class File extends Base {
	public File(String name, int size){ super(name, size);}

}
class Directory extends Base {
	List<Base> list = new ArrayList<>();
	
	public Directory(String name){ super(name, 0); }
	
	public void add(Base b) {
		list.add(b);
		size += b.getSize();
	}
}*/
abstract class Item{
	public String name;
	public Item(String name) { this.name = name;}
	public abstract int getSize();
}
class File extends Item{
	private int size;
	public File(String name, int size) {
		super(name);
		this.size=size;	
	}
	@Override
	public int getSize() { return size; }
}
class Directory extends Item{
	private List<Item> list = new ArrayList<>();
	public Directory(String name) { super(name);}
	public void add(Item item) { list.add(item);}
	@Override
	public int getSize() {
		int size=0;
		for(Item item: list) {
			size += item.getSize();
		}
		return size;
	}
}




