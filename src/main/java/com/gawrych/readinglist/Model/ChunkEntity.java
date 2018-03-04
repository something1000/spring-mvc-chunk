package com.gawrych.readinglist.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="chunks")
public class ChunkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "author")
    private User authorId;

    @NotNull
    @Column(name = "content")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthorID() {
        return authorId;
    }

    public void setAuthorID(User authorID) {
        this.authorId = authorID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
