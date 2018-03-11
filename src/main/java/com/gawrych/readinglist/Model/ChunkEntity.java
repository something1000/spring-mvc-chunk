package com.gawrych.readinglist.Model;


import com.gawrych.readinglist.Converters.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "replyTo")
    private Set<ChunkEntity> replies;

    @ManyToOne
    @JoinColumn(name="replyTo_id")
    private ChunkEntity replyTo;

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

    public LocalDateTime getPostdate() {
        return postdate;
    }

    public void setPostdate(LocalDateTime postdate) {
        this.postdate = postdate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public User getDeleter() {
        return deleter;
    }

    public void setDeleter(User deleter) {
        this.deleter = deleter;
    }

    public List<ChunkEntity> getReplies() {
        List<ChunkEntity> sorted = new ArrayList<>(replies);

        Collections.sort(sorted, (x, y)->{
            if(Duration.between(x.getPostdate(), y.getPostdate()).isNegative()){
                return -1;
            } else if(Duration.between(x.getPostdate(), y.getPostdate()).isZero()) {
                return 0;
            }
            return -1;
        } );
        return sorted;
    }

    public void setReplies(Set<ChunkEntity> replies) {
        this.replies = replies;
    }

    public ChunkEntity getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ChunkEntity replyTo) {
        this.replyTo = replyTo;
    }
}
