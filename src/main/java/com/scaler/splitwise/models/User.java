package com.scaler.splitwise.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USERS")
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String hashedPassword;
}
