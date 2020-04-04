package practice;

import java.io.Serializable;
import java.util.Date;

public class FortuneBean implements Serializable{

	private String fortune = "";
	private Date now = new Date();

	public FortuneBean() {};

	public void setToday(Date now) {
		this.now = now;
	}

	public void setFortune(String fortune) {
		this.fortune = fortune;
	}

	public Date getToday() {
		return this.now;
	}

	public String getFortune() {
		return this.fortune;
	}
}
