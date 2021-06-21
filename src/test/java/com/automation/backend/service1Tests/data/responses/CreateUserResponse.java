package com.automation.backend.service1Tests.data.responses;

import java.util.Date;

@lombok.Data
public class CreateUserResponse {
    public int code;
    public Object meta;
    public Data data;
}

@lombok.Data
class Data {
    public int id;
    public String name;
    public String email;
    public String gender;
    public String status;
    public Date created_at;
    public Date updated_at;
}

