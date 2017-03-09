package com.jeta.rest.entity;

/**
 * Created by yksenofontov on 01.03.2017.
 */
public class Comment {
    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;

    public boolean equals(Comment comment){
        return this.postId == comment.postId && this.name.equals(comment.name) && this.email.equals(comment.email) && this.body.equals(comment.body);
    }
}
