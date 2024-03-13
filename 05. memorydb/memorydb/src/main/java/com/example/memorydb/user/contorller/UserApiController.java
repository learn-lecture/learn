package com.example.memorydb.user.contorller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Business Logic
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("")
    public UserEntity create(@RequestBody UserEntity userEntity) {
        return userService.save(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll(@RequestParam(name = "score", defaultValue = "0") int score) {
        return userService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<UserEntity> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        userService.delete(id);
    }

    @GetMapping("/filter")
    public List<UserEntity> filterScore(@RequestParam int score){
        return userService.filter(score);
    }

    @GetMapping("/filtertwo")
    public List<UserEntity> filterScoreMinMax(
        @RequestParam int min,
        @RequestParam int max
    ){
        return userService.filter(min, max);
    }
}
/*
Business Logic을 처리하는 부분 = Service Logic
Service Logic은 Repasitory DataBase와 붙어있는 영역
Controller -> Service로 Request
Service -> DataBase(Repasitory)를 통해서 Data를 처리 후 Response
*/