package com.gawrych.readinglist.Model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gawrych.readinglist.Converters.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="chunk_replies")
public class ReplyChunkEntity implements IChunkEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @NotNull
    @Size(max=2500)
    @Column(name = "content")
    private String content;

    @Column(name = "date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime postdate;

    private boolean isDeleted;

    private String deleteReason;

    @ManyToOne
    @JoinColumn(name = "deleter_id")
    private User deleter;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="replyTo_id")
    private ChunkEntity replyTo;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public User getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public LocalDateTime getPostdate() {
        return this.postdate;
    }

    @Override
    public void setPostdate(LocalDateTime postdate) {
        this.postdate = postdate;
    }

    @Override
    public boolean isDeleted() {
        return this.isDeleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    @Override
    public String getDeleteReason() {
        return this.deleteReason;
    }

    @Override
    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    @Override
    public User getDeleter() {
        return this.deleter;
    }

    @Override
    public void setDeleter(User deleter) {
        this.deleter = deleter;
    }

    public ChunkEntity getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ChunkEntity replyTo) {
        this.replyTo = replyTo;
    }

    @JsonProperty(value = "username")
    public String getAuthorUsername(){
        return author.getUsername();
    }
}
