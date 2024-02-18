package com.unicam.risorseLocali.Core.Repository;

import com.unicam.risorseLocali.Core.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
