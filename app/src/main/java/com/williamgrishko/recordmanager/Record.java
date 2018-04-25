package com.williamgrishko.recordmanager;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Date;


@Entity(tableName = "records")
public class Record {

    @PrimaryKey(autoGenerate = true)
    private long recordID;

    private String name;

    private String description;

    @TypeConverters(Converters.class)
    private BigDecimal price;

    private int rating;

    @TypeConverters(Converters.class)
    private Date dateCreated;

    @TypeConverters(Converters.class)
    private Date dateModified;

    public long getRecordID() {
        return recordID;
    }

    public void setRecordID(long recordID) {
        this.recordID = recordID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {

        return getName() + " - " + getDescription();
    }

}
