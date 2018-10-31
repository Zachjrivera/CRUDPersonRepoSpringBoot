package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.Person;

import io.zipcoder.crudapp.interfaces.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController  {

   @Autowired
    PersonRepository personList;


    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public Person createperson(@RequestBody Person person){
    personList.save(person);

    return null;
    }

    @RequestMapping(value = "/person/{id}",method = RequestMethod.GET)
    public Person getPersonbyID(@PathVariable Long id){
        personList.findOne(id);
        return null;

    }

    @RequestMapping(value ="/people",method = RequestMethod.GET)
    public void getPersonList(){
        personList.findAll();
    }

    @RequestMapping(value = "/people/{id}",method = RequestMethod.PUT)
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
       personList.findOne(id);
       personList.delete(id);
       personList.save(person);
       return null;
    }

    @RequestMapping(value = "/people/{id}",method = RequestMethod.DELETE)
    public void deleteperson(@PathVariable Long id) {
        personList.delete(id);
    }

}
