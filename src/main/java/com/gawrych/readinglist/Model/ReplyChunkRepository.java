package com.gawrych.readinglist.Model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyChunkRepository extends PagingAndSortingRepository<ReplyChunkEntity, Long> {
    ReplyChunkEntity findById(Long id);
    List<ReplyChunkEntity> findByAuthor(User user);
    List<ReplyChunkEntity> findByAuthor_Username(String username);
}
