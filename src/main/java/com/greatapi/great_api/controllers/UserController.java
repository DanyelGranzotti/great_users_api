package com.greatapi.great_api.controllers;

import com.greatapi.great_api.dtos.UserDto;
import com.greatapi.great_api.exceptions.NameAlreadyExistsException;
import com.greatapi.great_api.models.UserModel;
import com.greatapi.great_api.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDate.now(ZoneId.of("UTC")));
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/user")
                .buildAndExpand(userModel.getId())
                .toUri()).body(userService.save(userModel));
    }

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(@PageableDefault(value=0, size=20,sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable (value="id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<UserModel> getUserByRg(@PathVariable (value="rg") String rg) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByRg(rg));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserModel> getUserByCpf(@PathVariable (value="cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByCpf(cpf));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<UserModel>> getUserByName(@PathVariable (value="name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByNameContains(name));
    }

    /*public ResponseEntity<List<Laptop>> getLaptopsByNameContains(@RequestParam String name) {
        return new ResponseEntity<List<Laptop>>(lRepo.findByNameContaining(name), HttpStatus.OK);
    }
*/
    @PutMapping("/id/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable (value="id") UUID id, @RequestBody @Valid UserDto userDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userService.put(id, userModel));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable (value="id") UUID id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @DeleteMapping("/rg/{rg}")
    public ResponseEntity<Boolean> deleteUserByRg(@PathVariable (value="rg") String rg) {
        userService.deleteByRg(rg);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Boolean> deleteUserByCpf(@PathVariable (value="cpf") String cpf) {
        userService.deleteByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
