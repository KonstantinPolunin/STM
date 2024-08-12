package com.stm.tickets.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    private Integer id;
    private String role;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return getRole();
    }

}
