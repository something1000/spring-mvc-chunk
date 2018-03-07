package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.ChunkEntity;
import com.gawrych.readinglist.Model.ChunkRepository;
import com.gawrych.readinglist.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("chunkService")
public class ChunkService {
    private ChunkRepository chunkRepository;
    private UserService userService;
    @Autowired
    public ChunkService(ChunkRepository chunkRepository, UserService userService) {
        this.chunkRepository = chunkRepository;
        this.userService = userService;
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
    public Page<ChunkEntity> getChunkPage(int pageNumber, int pageSize){

        PageRequest page = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "postdate");
        return chunkRepository.findAll(page);
    }
    public Long getNumberOfChunks(){
        return chunkRepository.count();
    }

    @Transactional
    public Boolean deleteById(Long id, String reason, String username){
        ChunkEntity toDelete = chunkRepository.findById(id);

        if(toDelete == null) return false;

        toDelete.setDeleted(true);
        toDelete.setDeleteReason(reason);
        toDelete.setDeleter(userService.findByUsername(username));
        chunkRepository.save(toDelete);
        return true;
    }
}
