package Refactoring;

public class Test1 {
	public static void main(String[] args) {
		new Server().run();
	}
}

// 이전 코드는 로그를 출력할 때마다 로그 객체에 대하여 널 체크를 수행하고 있습니다.
// 이는 코드의 복잡도를 높이고 널참조로 인한 예외가 발생할 수 있기 때문에 좋은 방법이 아닙니다.
// 이를 해결하기 위해 아무 것도 하지 않는 객체인 널 객체를 도입해보도록 합니다.
// Refactoring: Introduce Null Object

// 널 객체와 로그 객체를 하나의 타입으로 처리하고 로그 인터페이스를 자식이 구현하도록 인터페이스 도입
interface Logger {
	public void error(String msg);
	public void warn(String msg);
	public void info(String msg);
	
	
}
// 이제 로그 객체를 설계하는 사람은 Logger 객체를 구현하도록 합니다.
class LoggerImpl implements Logger {
	public void error(String msg) { System.out.println("[ERR] "+msg); }
	public void warn(String msg) { System.out.println("[WARN] "+msg); }
	public void info(String msg) { System.out.println("[INFO] "+msg); }
}
// 아래와 같이 인터페이스만 있을 뿐 아무런 동작을 하지 않는 객체를 널 객체라고 합니다.
class NullLogger implements Logger {
	@Override
	public void error(String msg) { /* Nothing to do */ }
	@Override
	public void warn(String msg) { /* Nothing to do */ }
	@Override
	public void info(String msg) { /* Nothing to do */ }	
}

class Server{
	private Logger logger = null;
	
	public Server(Logger l) { this.logger = l; }
	
	// 사용자가 로그 정책을 설정하지 않는 경우, 널 객체를 사용하도록 합니다.
	public Server() { this.logger = new NullLogger(); }
	
	public void run() {
		while(true) {
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			System.out.println(".");
			
			// 에러가 발생한 경우
//			if(logger != null)
				logger.error("ERROR 1");
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			
			// .. 
			// 에러가 발생한 경우
//			if(logger != null)
				logger.error("ERROR 2");
		}
	}
}

/*
public class Test1 {
	public static void main(String[] args) {
		new Server().run();
	}
}

// 발생된 에러에 대하여 정보를 모니터가 아닌 다른 곳을 출력하기 위해 로그를 기록하는 클래스를 도입.
class Logger {
	public void error(String msg) { System.out.println("[ERR] "+msg); }
	public void warn(String msg) { System.out.println("[WARN] "+msg); }
	public void info(String msg) { System.out.println("[INFO] "+msg); }
}
class Server{
	private Logger logger = null;
	
	// 로그 설정 정책을 사용자가 결정할 수 있도록 아래와 같이 생성자를 2개 추가합니다.
	public Server(Logger l) { this.logger = l; }
	public Server() {}
	
	public void run() {
		while(true) {
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			System.out.println(".");
			
			// 에러가 발생한 경우
//			System.err.println("ERROR 1");
			if(logger != null) {
				logger.error("ERROR 1");
			}
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			
			// .. 
			// 에러가 발생한 경우
//			System.err.println("ERROR 2");
			if(logger != null) {
				logger.error("ERROR 2");
			}
		}
	}
}*/

//public class Test1 {
//
//	public static void main(String[] args) {
//		new Server().run();
//	}
//}
//
//// 어떤 기능을 제공하는 서버가 있다고 가정합니다.
//class Server{
//	
//	// 서버를 구동하려면 아래의 메서드를 호출하면 됩니다.
//	public void run() {
//		// 일반적으로 서버는 종료되지 않고 계속 실행되면서 요청을 처리해야 하므로 아래와 같이 무한 루프를 수행합니다.
//		while(true) {
//			try { Thread.sleep(1000); } catch (InterruptedException e) { }
//			System.out.println(".");
//			
//			// 에러가 발생한 경우
//			System.err.println("ERROR 1");
//			
//			// .. 
//			// 에러가 발생한 경우
//			System.err.println("ERROR 2");
//		}
//	}
//}
