package com.greatapi.great_api.controllers;

import com.greatapi.great_api.dtos.UserDto;
import com.greatapi.great_api.models.UserModel;
import com.greatapi.great_api.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        if (userService.existsByName(userDto.getName())) {
            return ResponseEntity.status(400).body("Name already exists");
        }
        if (userService.existsByCpf(userDto.getCpf())) {
            return ResponseEntity.status(400).body("CPF already exists");
        }
        if (userService.existsByRg(userDto.getRg())) {
            return ResponseEntity.status(400).body("RG already exists");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(value=0, size=20,sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable (value="id") UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable (value="id") UUID id, @RequestBody @Valid UserDto userDto) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var userModel = userModelOptional.get();
        if (!userModel.getName().equals(userDto.getName()) && userService.existsByName(userDto.getName())) {
            return ResponseEntity.status(400).body("Name already exists");
        }
        if (!userModel.getCpf().equals(userDto.getCpf()) && userService.existsByCpf(userDto.getCpf())) {
            return ResponseEntity.status(400).body("CPF already exists");
        }
        if (!userModel.getRg().equals(userDto.getRg()) && userService.existsByRg(userDto.getRg())) {
            return ResponseEntity.status(400).body("RG already exists");
        }
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable (value="id") UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }

}
