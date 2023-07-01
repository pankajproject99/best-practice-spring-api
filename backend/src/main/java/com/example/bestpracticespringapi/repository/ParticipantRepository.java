package com.example.bestpracticespringapi.repository;

import com.example.bestpracticespringapi.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
