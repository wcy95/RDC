package design.observer;

import java.util.Observable;

public class Observer implements java.util.Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("捕捉到变化");
		System.out.println(o);
		System.out.println(arg);
	}

	@Override
	public String toString() {
		return "Observer []";
	}

}
