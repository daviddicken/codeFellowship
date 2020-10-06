package com.daviddicken.codeFellowship.models.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    public  Users findByUserName(String username);
}
