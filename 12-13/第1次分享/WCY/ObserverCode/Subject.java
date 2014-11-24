package design.observer;

import java.util.Observable;

public class Subject extends Observable {

	private String subjectName;
	private String subjectAge;
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
		this.setChanged();
		this.notifyObservers(new Subject());
	}
	
	public String getSubjectAge() {
		return subjectAge;
	}
	
	public void setSubjectAge(String subjectAge) {
		this.subjectAge = subjectAge;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	@Override
	public String toString() {
		return "Subject [subjectName=" + subjectName + ", subjectAge="
				+ subjectAge + "]";
	}
		
}
