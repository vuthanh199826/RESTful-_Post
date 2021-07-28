package com.codegym.cms.repository;

import com.codegym.cms.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends PagingAndSortingRepository<Post, Long> {
}
