package com.example.LocalExpertBackend.visitor.comments_about_customer;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment_customer")
public class VisitorCommentsAboutCustomerCommentCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double rate;

    @NonNull
    private Date addedDate;
}
