package com.tiberiumaxim.petclinic.services.springdatajpa;

import com.tiberiumaxim.petclinic.models.Owner;
import com.tiberiumaxim.petclinic.repositories.OwnerRepository;
import com.tiberiumaxim.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSpringDataJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {

        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        return ownerOptional.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
