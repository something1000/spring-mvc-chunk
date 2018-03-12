package com.gawrych.readinglist.Model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

public interface IChunkEntity {
    public Long getId();

    public void setId(Long id);

    public User getAuthor();

    public void setAuthor(User author);

    public String getContent();

    public void setContent(String content);

    public LocalDateTime getPostdate();

    public void setPostdate(LocalDateTime postdate);

    public boolean isDeleted();

    public void setDeleted(boolean deleted);

    public String getDeleteReason();

    public void setDeleteReason(String deleteReason);

    public User getDeleter();

    public void setDeleter(User deleter);
}
