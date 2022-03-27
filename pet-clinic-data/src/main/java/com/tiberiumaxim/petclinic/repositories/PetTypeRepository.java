package com.tiberiumaxim.petclinic.repositories;

import com.tiberiumaxim.petclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
