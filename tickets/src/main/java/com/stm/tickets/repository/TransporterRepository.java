package com.stm.tickets.repository;

import com.stm.tickets.models.Transporter;

public interface TransporterRepository {
    void addTransporter(Transporter transporter);
    void updateTransporter(Long id, Transporter transporter);
    void deleteTransporter(Long id);
}
