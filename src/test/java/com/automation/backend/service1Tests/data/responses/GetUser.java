package com.automation.backend.service1Tests.data.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUser {
    public int code;
    public Meta meta;
    public List<Data1> data;
}

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Meta {
    public Pagination pagination;
}

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Pagination {
    public int total;
    public int pages;
    public int page;
    public int limit;
}

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Data1 {
    public int id;
    public String name;
    public String email;
    public String gender;
    public String status;
    public Date created_at;
    public Date updated_at;
}

