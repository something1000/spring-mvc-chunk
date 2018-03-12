package com.gawrych.readinglist.Services;

import com.gawrych.readinglist.Model.ReplyChunkEntity;
import com.gawrych.readinglist.Model.ReplyChunkRepository;
import com.gawrych.readinglist.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyChunkService")
public class ReplyChunkService {

    private ReplyChunkRepository replyChunkRepository;

    @Autowired
    public ReplyChunkService(ReplyChunkRepository replyChunkRepository) {
        this.replyChunkRepository = replyChunkRepository;
    }

    public ReplyChunkEntity findById(Long id){
        return replyChunkRepository.findById(id);
    }

    public List<ReplyChunkEntity> findByAuthor(User user){
        return replyChunkRepository.findByAuthor(user);
    }

    public List<ReplyChunkEntity> findByUsername(String username){
        return replyChunkRepository.findByAuthor_Username(username);
    }
}
