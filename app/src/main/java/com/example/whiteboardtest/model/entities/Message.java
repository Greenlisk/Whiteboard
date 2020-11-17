package com.example.whiteboardtest.model.entities;

import java.util.Date;
import java.util.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "message_table")
public class Message implements Comparable<Message>{

    @PrimaryKey(autoGenerate = true)
    private Integer uuid;

    private Date date;

    private String name;


    private String subject;

    public Message(Date date, String name, String subject) {
        this.date = date;
        this.name = name;
        this.subject = subject;
    }

    @Override
    public int compareTo(Message o) {
        int ret = 0;
        long result = this.date.getTime() - o.getDate().getTime();
        if (result > 0){
            ret = -1;
        } else {
            ret = 1;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getUuid().equals(message.getUuid()) &&
                getDate().equals(message.getDate()) &&
                getName().equals(message.getName()) &&
                getSubject().equals(message.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getDate(), getName(), getSubject());
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
