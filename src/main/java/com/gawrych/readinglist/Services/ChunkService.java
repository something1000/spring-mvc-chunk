package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Model.ChunkRepository;
import com.gawrych.readinglist.Model.User;
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

    public Set<ChunkEntity> findChunkByAuthor(User author){
        return chunkRepository.findByAuthor(author);
    }

    public ChunkEntity findChunkById(Long id){
        return chunkRepository.findById(id);
    }

    public void saveChunk(ChunkEntity chunk){
        chunkRepository.save(chunk);
    }
}
