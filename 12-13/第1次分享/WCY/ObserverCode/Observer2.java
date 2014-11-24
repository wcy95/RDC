package design.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer2 implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("这是第二个观察者");
		Subject subject = (Subject) arg;
		System.out.println("有新博客提醒！");
		System.out.println("姓名：" + subject.getSubjectName() +"年龄：" + subject.getSubjectAge());
	}

}
