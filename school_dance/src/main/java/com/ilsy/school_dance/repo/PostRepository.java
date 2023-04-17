package com.ilsy.school_dance.repo;

import com.ilsy.school_dance.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
