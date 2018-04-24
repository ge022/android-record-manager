package com.williamgrishko.recordmanager;

import android.arch.persistence.room.TypeConverter;

import java.math.BigDecimal;
import java.util.Date;

public class Converters {

    @TypeConverter
    public BigDecimal fromLong(Long value) {
        return value == null ? null : new BigDecimal(value).divide(new BigDecimal(100));
    }

    @TypeConverter
    public Long toLong(BigDecimal bigDecimal) {
        return bigDecimal == null ? null : bigDecimal.multiply(new BigDecimal(100)).longValue();
    }

    @TypeConverter
    public Date fromTimeStamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
