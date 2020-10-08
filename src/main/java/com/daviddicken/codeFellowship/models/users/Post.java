package com.daviddicken.codeFellowship.models.users;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    public Users user;

    Date createdAt;
    String body;

    //============== Constructors ====================
    public Post(){};

    public Post(String body) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        this.createdAt = date;
        this.body = body;
    }
    //========== toString =============================
    public String toString(){
//        String timeStamp = createdAt.toString();
        return body + "    Posted on: " + createdAt;
    }

    //========== Getters & Setters =====================

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
