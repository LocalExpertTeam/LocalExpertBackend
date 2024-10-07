package com.local_expert.backend.customer_data.chat_list;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("companyChatListContractRepository")
interface CompanyChatListContractRepository extends PagingAndSortingRepository<CompanyChatListContractEntity, Long> {

    Page<CompanyChatListContractEntity> findAllByCompanyId(long id, Pageable pageable);
}
