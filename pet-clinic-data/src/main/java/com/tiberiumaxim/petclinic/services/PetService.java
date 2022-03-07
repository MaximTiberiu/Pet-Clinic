package com.tiberiumaxim.petclinic.services;

import com.tiberiumaxim.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
