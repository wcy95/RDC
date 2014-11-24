package design.observer;


public class Test {

	public static void main(String[] args) {
		Subject subject = new Subject();
//		subject.addObserver(new Observer2());
		subject.addObserver(new Observer());		
//		subject.addObserver(new Observer2());
//		System.out.println(subject.countObservers());
		
		subject.setSubjectName("李明");
		subject.setSubjectAge("11");
	}
}
