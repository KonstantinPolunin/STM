package com.stm.tickets.service;

import com.stm.tickets.models.Transporter;
import com.stm.tickets.repository.TransporterRepository;
import org.springframework.stereotype.Service;

@Service
public class TransporterService {
    private final TransporterRepository transporterRepository;

    public TransporterService(TransporterRepository transporterRepository) {
        this.transporterRepository = transporterRepository;
    }


    public void addTransporter(Transporter transporter) {
        transporterRepository.addTransporter(transporter);
    }

    public void updateTransporter(Long id, Transporter transporter) {
        transporterRepository.updateTransporter(id, transporter);
    }

    public void deleteTransporter(Long id) {
        transporterRepository.deleteTransporter(id);
    }
}
