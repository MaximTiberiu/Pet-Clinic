package com.tiberiumaxim.petclinic.services;

import com.tiberiumaxim.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
