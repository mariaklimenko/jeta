package com.jeta.rest.entity;

/**
 * Created by yksenofontov on 01.03.2017.
 */
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public boolean equals(Comment comment){
        return this.postId == comment.postId && this.name.equals(comment.name) && this.email.equals(comment.email) && this.body.equals(comment.body);
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {

        return body;
    }
}
