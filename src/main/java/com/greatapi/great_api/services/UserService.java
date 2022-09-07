package com.greatapi.great_api.services;

import com.greatapi.great_api.models.UserModel;
import com.greatapi.great_api.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    public boolean existsByRg(String rg) {
        return userRepository.existsByRg(rg);
    }

    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
