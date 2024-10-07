package com.local_expert.backend.customer_data.chat_list;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "message")
class CompanyChatListMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date creationDate;

    @ManyToMany
    @JoinTable(
            name = "user_who_do_not_view_message",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<CompanyChatListAccountEntity> usersWhoDoNotView;
}
