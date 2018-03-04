package com.gawrych.readinglist.Model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="chunks")
public class ChunkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @NotNull
    @Size(max=500)
    @Column(name = "content")
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
