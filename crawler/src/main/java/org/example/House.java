package org.example;

import com.google.gson.annotations.SerializedName;

public class House {

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWardName() {
        return wardName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @SerializedName("floors")
    private int floors;

    @SerializedName("rooms")
    private int rooms;

    @SerializedName("area_name")
    private String areaName;

    @SerializedName("region_name")
    private String regionName;

    @SerializedName("price")
    private long price;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("width")
    private double width;

    @SerializedName("length")
    private double length;

    @SerializedName("size")
    private int size;

    @SerializedName("ward_name")
    private String wardName;

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
// Thêm các getter và setter nếu cần
}
