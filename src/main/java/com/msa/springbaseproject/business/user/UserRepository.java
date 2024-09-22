package com.msa.springbaseproject.business.user;

import com.msa.springbaseproject.business.user.model.entity.User;
import com.msa.springbaseproject.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);
}
