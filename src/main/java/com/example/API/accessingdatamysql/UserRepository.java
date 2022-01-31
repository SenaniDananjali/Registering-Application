package com.example.API.accessingdatamysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.Collection;
import java.util.List;


public interface UserRepository extends CrudRepository<User, String> {
    @Query( value="SELECT *  from user u where u.email= :email", nativeQuery=true)
    List<User> getUserByEmail(@Param("email") String email);

}


