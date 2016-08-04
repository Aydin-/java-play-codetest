package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

/**
 * Created by aydingungordu on 8/3/16.
 */
public class Customer implements Comparable<Customer> {
    private long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private DateTime duetime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private DateTime jointime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDuetime() {
        return duetime;
    }

    public void setDuetime(DateTime duetime) {
        this.duetime = duetime;
    }

    public DateTime getJointime() {
        return jointime;
    }

    public void setJointime(DateTime jointime) {
        this.jointime = jointime;
    }

    @Override
    public int compareTo(Customer that) {
        return this.duetime.compareTo(that.getDuetime());
    }
}
