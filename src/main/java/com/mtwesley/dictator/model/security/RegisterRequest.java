package com.mtwesley.dictator.model.security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String username;
    private String password;
}
