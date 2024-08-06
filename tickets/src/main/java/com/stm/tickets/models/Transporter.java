package com.stm.tickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transporter {

    private Long id;
    private String name;
    private String phoneNumber;
}
