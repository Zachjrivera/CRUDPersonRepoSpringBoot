package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.Person;

import io.zipcoder.crudapp.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class PersonController  {

   @Autowired
    PersonRepository personRepository;

//Create Person
    @RequestMapping(value = "/people",method = RequestMethod.POST)
    public ResponseEntity createperson(@RequestBody Person person){
    personRepository.save(person);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>("Creating Person", HttpStatus.CREATED);
    }
//Get person by id
    @RequestMapping(value = "/people/{id}",method = RequestMethod.GET)
    public ResponseEntity getPersonbyID(@PathVariable Integer id){
        Person p = personRepository.findOne(id);
        return new ResponseEntity<>("Getting Person by ID", HttpStatus.OK) ;

    }
////Get all of the people
    @RequestMapping(value ="/people",method = RequestMethod.GET)
    public ResponseEntity<?> getallpeople(){

        Iterable<Person> allPolls = personRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

//update person
    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@RequestBody Person person, @PathVariable Integer id) {

           Person p = personRepository.findOne(id);
           if (p.getId() == id){
               personRepository.save(person);
               return new ResponseEntity<>("Updating",HttpStatus.OK);
           }else personRepository.save(person);
           return new ResponseEntity<>("Createing", HttpStatus.CREATED);


    }


//delete person
    @RequestMapping(value = "/people/{id}",method = RequestMethod.DELETE)
    public void deleteperson(@PathVariable Integer id) {
        personRepository.delete(id);
    }

}
