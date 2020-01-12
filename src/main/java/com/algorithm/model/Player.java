package com.algorithm.model;

//I Can use Builder Design Pattern but there 
//is no need because of less property in this class.
public class Player {

	private Integer skill;
	private boolean isShoted;

	public Player(Integer skill, boolean isShoted) {
		super();
		this.skill = skill;
		this.isShoted = isShoted;
	}

	public Integer getSkill() {
		return skill;
	}

	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	public boolean isShoted() {
		return isShoted;
	}

	public void setShoted(boolean isShoted) {
		this.isShoted = isShoted;
	}

	@Override
	public String toString() {
		return "Player [skill=" + skill + ", isShoted=" + isShoted + "]";
	}

}
