package design.observer;

import java.util.Observable;

public class Bloc extends Observable {

	public void BlocNotice(Subject subject) {
		System.out.println("姓名:" + subject.getSubjectName() + "年龄：" + subject.getSubjectAge());
		this.setChanged();
		this.notifyObservers(subject);
	}
}
