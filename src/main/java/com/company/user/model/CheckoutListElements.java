package com.company.user.model;

public class CheckoutListElements {
	private int projectId;
	private String projectName;
	private boolean projectPayed;
	private double projectPrice;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public boolean isProjectPayed() {
		return projectPayed;
	}
	public void setProjectPayed(boolean projectPayed) {
		this.projectPayed = projectPayed;
	}
	public double getProjectPrice() {
		return projectPrice;
	}
	public void setProjectPrice(double projectPrice) {
		this.projectPrice = projectPrice;
	}
	

}
