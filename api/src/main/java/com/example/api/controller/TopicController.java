package com.example.api.controller;

import com.example.api.domain.topic.Topic;
import com.example.api.domain.topic.TopicData;
import com.example.api.domain.topic.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<Topic> registerTopic(@RequestBody @Valid TopicData topicData,
                                               UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicRepository.save(new Topic(topicData));
        Topic responseTopic = new Topic(topic.getId(), topic.getTopic(), topic.getTitle(),topic.getContent());
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(responseTopic);
    }

    @GetMapping
    public ResponseEntity<Topic> getAllTopics(@PageableDefault(size=3)Pageable pageable){
        return ResponseEntity.ok((Topic) topicRepository.findAll(pageable).map(Topic::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Topic> updateTopic(@RequestBody @Valid Topic topic){
        Topic toUpdateTopic = topicRepository.getReferenceById(topic.getId());
        toUpdateTopic.updateData(topic);
        return ResponseEntity.ok(new Topic(toUpdateTopic.getId(),toUpdateTopic.getTopic(),
                toUpdateTopic.getTitle(),toUpdateTopic.getContent()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Topic> deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.disableTopic();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getAllTopics(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        var topics = new Topic(topic.getId(), topic.getTopic(), topic.getTitle(), topic.getContent());
        return ResponseEntity.ok(topics);
    }
}
