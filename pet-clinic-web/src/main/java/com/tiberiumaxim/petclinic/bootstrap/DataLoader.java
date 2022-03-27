package com.tiberiumaxim.petclinic.bootstrap;

import com.tiberiumaxim.petclinic.model.Owner;
import com.tiberiumaxim.petclinic.model.Pet;
import com.tiberiumaxim.petclinic.model.PetType;
import com.tiberiumaxim.petclinic.model.Vet;
import com.tiberiumaxim.petclinic.services.OwnerService;
import com.tiberiumaxim.petclinic.services.PetTypeService;
import com.tiberiumaxim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;

	private final VetService vetService;

	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);

		System.out.println("Loaded Pet Types...");

		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Bricks Street");
		owner1.setCity("Miami");
		owner1.setTelephone("123456789");

		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");

		owner1.getPets().add(mikesPet);
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Smith");
		owner2.setAddress("13 Victory Street");
		owner2.setCity("New York");
		owner2.setTelephone("987654321");

		Pet fionasCat = new Pet();
		fionasCat.setPetType(savedCatPetType);
		fionasCat.setOwner(owner2);
		fionasCat.setBirthDate(LocalDate.now());
		fionasCat.setName("Lisi");

		owner2.getPets().add(fionasCat);
		ownerService.save(owner2);

		System.out.println("Loaded Owners...");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Maria");
		vet2.setLastName("Porter");

		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}

}
