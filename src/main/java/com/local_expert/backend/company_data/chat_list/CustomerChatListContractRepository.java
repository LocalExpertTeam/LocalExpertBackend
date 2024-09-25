package com.local_expert.backend.company_data.chat_list;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("customerCooperatorListContractRepository")
interface CustomerChatListContractRepository extends PagingAndSortingRepository<CustomerChatListContractEntity, Long> {

    Page<CustomerChatListContractEntity> findAllByCustomerId(long id, Pageable pageable);
}
