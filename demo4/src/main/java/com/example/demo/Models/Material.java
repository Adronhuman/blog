package com.example.demo.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Material {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String name,link;
    private LocalDateTime date;
    @Lob
    private String tags;
    public Material(){

    }
    public Material(String name,String link,LocalDateTime date,String tags){
        this.date = date;
        this.link = link;
        this.name = name;
        this.tags = tags;
    }

    public String[] getTagsInWords(){
        return tags.split(",");
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
