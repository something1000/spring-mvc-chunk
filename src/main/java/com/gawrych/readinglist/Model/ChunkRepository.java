package com.gawrych.readinglist.Model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChunkRepository extends PagingAndSortingRepository<ChunkEntity, Long> {
       Set<ChunkEntity> findByAuthor(User author);
       ChunkEntity findById(Long id);
       Long deleteById(Long id);
}
