package com.greatapi.great_api.services;

import com.greatapi.great_api.exceptions.CpfAlreadyExistsException;
import com.greatapi.great_api.exceptions.NameAlreadyExistsException;
import com.greatapi.great_api.exceptions.ObjectNotFoundException;
import com.greatapi.great_api.exceptions.RgAlreadyExistsException;
import com.greatapi.great_api.models.UserModel;
import com.greatapi.great_api.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
        if (userRepository.findByName(userModel.getName()).isPresent()) {
            throw new NameAlreadyExistsException(userModel.getName());
        }
        if (userRepository.findByCpf(userModel.getCpf()).isPresent()) {
            throw new CpfAlreadyExistsException(userModel.getCpf());
        }
        if (userRepository.findByRg(userModel.getRg()).isPresent()) {
            throw new RgAlreadyExistsException(userModel.getRg());
        }
        return userRepository.save(userModel);
    }

    @Transactional
    public UserModel put(UUID id,UserModel userModel) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        if (!user.getName().equals(userModel.getName()) && userRepository.existsByName(userModel.getName())) {
            throw new NameAlreadyExistsException(userModel.getName());
        }
        if (!user.getCpf().equals(userModel.getCpf()) && userRepository.existsByCpf(userModel.getCpf())) {
            throw new CpfAlreadyExistsException(userModel.getCpf());
        }
        if (!user.getRg().equals(userModel.getRg()) && userRepository.existsByRg(userModel.getRg())) {
            throw new RgAlreadyExistsException(userModel.getRg());
        }
        user.setName(userModel.getName());
        user.setCpf(userModel.getCpf());
        user.setRg(userModel.getRg());
        return userRepository.save(user);
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

    public UserModel findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public UserModel findByRg(String rg) {
        return userRepository.findByRg(rg).orElseThrow(() -> new ObjectNotFoundException(rg));
    }

    public UserModel findByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(cpf));
    }

    public List<UserModel> findByNameContains(String name) {
        return userRepository.findByNameContaining(name);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }


    public void deleteByRg(String rg) {
        UserModel user = userRepository.findByRg(rg).orElseThrow(() -> new ObjectNotFoundException(rg));
        userRepository.delete(user);
    }

    public void deleteByCpf(String cpf) {
        UserModel user = userRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(cpf));
        userRepository.delete(user);
    }



}
