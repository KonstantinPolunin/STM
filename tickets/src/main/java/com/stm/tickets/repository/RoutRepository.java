package com.stm.tickets.repository;


import com.stm.tickets.models.Rout;

public interface RoutRepository {
    void addRout(Rout rout);
    void updateRout(Long id ,Rout rout);
    void deleteRout(Long id);
}
