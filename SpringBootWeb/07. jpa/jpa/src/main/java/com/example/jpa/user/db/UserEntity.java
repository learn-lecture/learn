package com.example.jpa.user.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// mysql에서 매핑할 TableName ORM
@Entity(name = "user")
public class UserEntity {
    // Primary Key
    @Id
    // MYSQL == IDENTITY, Auto Generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
