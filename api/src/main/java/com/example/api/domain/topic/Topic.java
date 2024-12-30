package com.example.api.domain.topic;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="topic")
@Entity(name="topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic;
    private String title;
    private String content;

    public Topic(TopicData topicData) {
        this.topic = topicData.topic();
        this.title = topicData.title();
        this.content = topicData.content();
    }

    public Topic(Topic topic) {
        this.id = topic.getId();
        this.topic = topic.getTopic();
        this.title = topic.getTitle();
        this.content = topic.getContent();
    }

    public Topic(Long id, String topic, String title, String content){
        this.id = id;
        this.topic = topic;
        this. title = title;
        this.content = content;
    }

    public Topic() {}

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void updateData(@Valid Topic topic) {
        if(topic.getTopic() != null) this.topic = topic.getTopic();

        if(topic.getTopic() != null) this.title = topic.getTitle();

        if(topic.getContent() != null) this.content = topic.getContent();
    }

    public void disableTopic() {
        this.topic = null;
        this.title = null;
        this.content = null;
    }
}
