package com.greatapi.great_api.repositories;

import com.greatapi.great_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByName(String name);

    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);

}
