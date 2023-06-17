package com.example.bestpracticespringapi.repository;

import com.example.bestpracticespringapi.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
