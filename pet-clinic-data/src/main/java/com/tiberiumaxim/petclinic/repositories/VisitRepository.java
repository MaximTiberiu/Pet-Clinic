package com.tiberiumaxim.petclinic.repositories;

import com.tiberiumaxim.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
