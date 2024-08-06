package com.stm.tickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rout {

    private Long id;
    private String departure;
    private String destination;
    private Integer duration;
    private Transporter transporter;
}
