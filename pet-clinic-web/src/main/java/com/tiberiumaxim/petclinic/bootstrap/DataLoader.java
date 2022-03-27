package com.tiberiumaxim.petclinic.bootstrap;

import com.tiberiumaxim.petclinic.models.*;
import com.tiberiumaxim.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;

	private final VetService vetService;

	private final PetTypeService petTypeService;

	private final SpecialtyService specialtyService;

	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
					  SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {

		int count = petTypeService.findAll().size();
		if (count == 0) {
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);

		System.out.println("Loaded Pet Types...");

		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology = specialtyService.save(radiology);

		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty savedSurgery = specialtyService.save(surgery);

		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedDentistry = specialtyService.save(dentistry);

		System.out.println("Loaded Specialties...");

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

		Visit catVisit = new Visit();
		catVisit.setPet(fionasCat);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sick Kitty");
		visitService.save(catVisit);

		System.out.println("Loaded Visits...");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialties().add(savedRadiology);

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Maria");
		vet2.setLastName("Porter");
		vet2.getSpecialties().add(savedSurgery);

		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}

}
