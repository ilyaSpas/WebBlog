package com.ilsy.school_dance.repo;

import com.ilsy.school_dance.models.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
}
