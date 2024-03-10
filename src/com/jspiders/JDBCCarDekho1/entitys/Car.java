package com.jspiders.JDBCCarDekho1.entitys;

public class Car {
	private int carId;
	private String carName;
	private String carModel;
	private String carBrand;
	private int carPrice;
	private String fuelType;
	private String colour;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int id) {
		this.carId = id;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public int getCarPrice() {
		return carPrice;
	}
	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	@Override
	public String toString() {
		return "Car [id=" + carId + ", carName=" + carName + ", carModel=" + carModel + ", carBrand=" + carBrand
				+ ", carPrice=" + carPrice + ", fuelType=" + fuelType + ", colour=" + colour + "]";
	}

}
