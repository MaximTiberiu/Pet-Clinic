package com.tiberiumaxim.petclinic.services.springdatajpa;

import com.tiberiumaxim.petclinic.models.Specialty;
import com.tiberiumaxim.petclinic.repositories.SpecialtyRepository;
import com.tiberiumaxim.petclinic.services.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SpecialtySpringDataJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySpringDataJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {

        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {

        Optional<Specialty> specialtyOptional = specialtyRepository.findById(id);
        return specialtyOptional.orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
