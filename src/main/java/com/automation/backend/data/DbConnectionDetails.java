package com.automation.backend.data;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class DbConnectionDetails {

    String host;
    String dbName;
    String userName;
    String password;
    String dbPort;
}
