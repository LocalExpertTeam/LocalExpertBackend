package com.example.LocalExpertBackend.visitor.comments_about_customer;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class CommentAboutCustomer {

    private long commentId;
    private long authorId;
    private String authorName;
    private String authorProfilePicture;
    private String text;
    private Date creationDate;
    private String contractStatus;
    private Double rate;

}
