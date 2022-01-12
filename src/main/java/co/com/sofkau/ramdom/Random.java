package co.com.sofkau.ramdom;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Random implements Serializable {
    
    @Id
    private String id;
    private String date;
    private String orderedList;
    private String randomList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(String orderedList) {
        this.orderedList = orderedList;
    }

    public String getRandomList() {
        return randomList;
    }

    public void setRandomList(String randomList) {
        this.randomList = randomList;
    }
}
