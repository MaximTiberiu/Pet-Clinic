package com.tiberiumaxim.petclinic.services.springdatajpa;

import com.tiberiumaxim.petclinic.models.Owner;
import com.tiberiumaxim.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSpringDataJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSpringDataJpaService service;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = service.findAll();
        returnOwnerSet.add(Owner.builder().id(1L).build());
        returnOwnerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, returnOwnerSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(ownerId);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(ownerId);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(ownerId).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        Owner owner = Owner.builder().id(ownerId).lastName(lastName).build();
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner smith = service.findByLastName(lastName);
        assertEquals(lastName, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}