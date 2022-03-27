package com.tiberiumaxim.petclinic.repositories;

import com.tiberiumaxim.petclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
