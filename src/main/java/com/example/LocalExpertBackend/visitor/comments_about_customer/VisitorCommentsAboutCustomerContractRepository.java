package com.example.LocalExpertBackend.visitor.comments_about_customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("visitorCommentsAboutCustomerContractRepository")
interface VisitorCommentsAboutCustomerContractRepository extends
        PagingAndSortingRepository<VisitorCommentsAboutCustomerContractEntity, Long> {

    Page<VisitorCommentsAboutCustomerContractEntity> findAllByCustomerIdAndCustomerCommentIdIsNotNull(Long customerId, Pageable pageable);
}
