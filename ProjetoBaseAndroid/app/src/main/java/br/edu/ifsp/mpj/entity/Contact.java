package br.edu.ifsp.mpj.entity;

import com.orm.SugarRecord;

/**
 * Created by falvojr on 5/11/17.
 */
public class Contact extends SugarRecord {

    private String name;
    private String phone;
    private Double latitude;
    private Double longitude;

    public Contact() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
