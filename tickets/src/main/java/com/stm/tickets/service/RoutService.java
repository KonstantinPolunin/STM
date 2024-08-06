package com.stm.tickets.service;

import com.stm.tickets.models.Rout;
import com.stm.tickets.repository.RoutRepository;
import org.springframework.stereotype.Service;

@Service
public class RoutService {
    private final RoutRepository routRepository;

    public RoutService(RoutRepository routRepository) {
        this.routRepository = routRepository;
    }

    public void addRout(Rout rout) {
        routRepository.addRout(rout);
    }

    public void updateRout(Long id, Rout rout) {
        routRepository.updateRout(id, rout);
    }

    public void deleteRout(Long id) {
        routRepository.deleteRout(id);
    }
}
