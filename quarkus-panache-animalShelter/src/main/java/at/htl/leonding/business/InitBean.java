package at.htl.leonding.business;

import at.htl.leonding.model.AnimalShelter;
import at.htl.leonding.model.Cage;
import at.htl.leonding.model.Cat;
import at.htl.leonding.model.Dog;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent ev){
        System.out.println("init-------------------------------init");

        AnimalShelter as1 = new AnimalShelter("Leonding", "Holzgasse 5", 4225);
        AnimalShelter as2 = new AnimalShelter("Linz", "Bachweg 99", 4060);
        AnimalShelter as3 = new AnimalShelter("Wien", "Schmiedgasse 75", 4545);

        Cage c1 = new Cage(5, 10);
        Cage c2 = new Cage(1, 2);
        Cage c3 = new Cage(2, 14);
        Cage c4 = new Cage(1, 9);
        Cage c5 = new Cage(3, 21);

        Dog d1 = new Dog("Mischling", 5, 10.2, "Luna", 150.0);
        Dog d2 = new Dog("Dackel", 14, 4.5, "Jessy", 255.0);
        Dog d3 = new Dog("Schaeferhund", 1, 14.8, "Rocky", 430.0);
        Dog d4 = new Dog("Mischling", 3, 9.8, "Axel", 130.0);

        Cat cat1 = new Cat("Persisch", 2, 3.1, "Lilly", 789.0);
        Cat cat2 = new Cat("Hauskatze", 8, 2.8, "Timmy", 200.0);
        Cat cat3 = new Cat("Hauskatze", 1, 4.0, "Lorenzo", 150.0);

        c1.addPet(d1);
        c1.addPet(d2);
        c2.addPet(d3);
        c3.addPet(cat1);
        c3.addPet(cat2);
        c4.addPet(d4);
        c5.addPet(cat3);

        as1.addCage(c1);
        as1.addCage(c2);
        as2.addCage(c3);
        as3.addCage(c4);
        as3.addCage(c5);

        em.persist(as1);
        em.persist(as2);
        em.persist(as3);
    }
}
