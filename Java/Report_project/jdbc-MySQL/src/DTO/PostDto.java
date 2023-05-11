package DTO;

import java.time.LocalDateTime;

public class PostDto {

    private int postId;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

    public PostDto(int userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public PostDto(int postId, int userId, String title, String content, LocalDateTime created, LocalDateTime updated) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}

