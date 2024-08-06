package com.stm.tickets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long id;
    private Rout rout;
    private LocalDateTime time;
    private Integer seat;
    private Integer price;
    private Boolean isBought;
    private Long userId;

}
