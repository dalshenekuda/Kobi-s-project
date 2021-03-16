package com.example.diplom.domain;

//public enum Role {
//    USER,ADMIN;
//}

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
//    USER,ADMIN;
    ROLE_USER,ROLE_ADMIN,ROLE_KOBI;
    @Override
    public String getAuthority() {
        return name();
    }
}