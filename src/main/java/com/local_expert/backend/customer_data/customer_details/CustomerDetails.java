package com.local_expert.backend.customer_data.customer_details;

import com.local_expert.backend.enums.ContactMethod;
import lombok.*;

import java.sql.Date;
import java.util.HashMap;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class CustomerDetails {

    private String firstName;
    private String lastName;
    private String profile;
    private String email;
    private String phone;
    private Date creationDate;
    private String preferredContactTime;
    private ContactMethod preferredContactMethod;
    private HashMap<Integer, Integer> ratesNumber;
    private int ratesTotalNumber;
}
