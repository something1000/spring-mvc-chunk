package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Model.ChunkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("chunkService")
public class ChunkService {
    private ChunkRepository chunkRepository;

    @Autowired
    public ChunkService(ChunkRepository chunkRepository) {
        this.chunkRepository = chunkRepository;
    }

    public Set<ChunkEntity> findChunkByAuthorId(Long authorId){
        return chunkRepository.findByAuthorId(authorId);
    }

    public ChunkEntity findChunkById(Long id){
        return chunkRepository.findById(id);
    }

    public void saveChunk(ChunkEntity chunk){
        chunkRepository.save(chunk);
    }
}
