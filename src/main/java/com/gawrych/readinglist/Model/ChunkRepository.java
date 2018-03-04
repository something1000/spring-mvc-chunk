package com.gawrych.readinglist.Model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChunkRepository extends PagingAndSortingRepository<ChunkEntity, Long> {
       public Set<ChunkEntity> findByAuthor(User author);
       public ChunkEntity findById(Long id);
}
