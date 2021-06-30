package com.xxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPassword {
    private String oldPass;
    private String pass;
    private String checkPass;
    private String adminId;
}
