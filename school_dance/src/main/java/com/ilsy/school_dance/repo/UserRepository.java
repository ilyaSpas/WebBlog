package com.ilsy.school_dance.repo;

import com.ilsy.school_dance.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
