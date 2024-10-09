package com.local_expert.backend.customer_data.chat_list;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChatElement {

    private long contractId;
    private String firstName;
    private String lastName;
    private String profile;
    private String status;
    private String title;
    private Date lastActualisation;
    private boolean containsNewMessage;
    private boolean isEvaluationPossible;
    private boolean isAnswerPossible;

}
