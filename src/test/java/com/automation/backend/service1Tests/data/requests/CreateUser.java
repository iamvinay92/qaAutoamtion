package com.automation.backend.service1Tests.data.requests;

import lombok.Data;

@Data
public class CreateUser {
    public String email;
    public String name;
    public String gender;
    public String status;
}
