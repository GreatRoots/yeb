package com.xxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {

        this.authority = authority;
    }
}
