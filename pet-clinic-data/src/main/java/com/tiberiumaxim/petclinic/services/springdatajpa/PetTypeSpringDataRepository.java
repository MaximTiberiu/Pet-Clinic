package com.tiberiumaxim.petclinic.services.springdatajpa;

import com.tiberiumaxim.petclinic.models.PetType;
import com.tiberiumaxim.petclinic.repositories.PetTypeRepository;
import com.tiberiumaxim.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSpringDataRepository implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSpringDataRepository(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {

        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {

        Optional<PetType> petTypeOptional = petTypeRepository.findById(id);
        return petTypeOptional.orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
