package com.avi.repository;

import com.avi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select p from User p where (LOWER(email) = LOWER(?1)) or (LOWER(emailId) = LOWER(?1))")
    public List<User> findRecordByEmail(String email);

    @Query(value = "select ps.selected_office ,ps.selected_role from authentication.user_entity ps join ua.user_access_mapping um on um.user_id = ps.entityid  where ps.entityid=:id", nativeQuery = true)
    List<Object[]> getUser(@Param(value = "id") Integer user);

    @Query(value = "select case when exists(select 1 from authentication.user_audit ua where requested_token=:token and is_logout=true) then true else false end as token_exist", nativeQuery = true)
    Boolean checkLogout(String token);
}
