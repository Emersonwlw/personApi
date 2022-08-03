package dio.personapi.controller;

import dio.personapi.dto.request.PersonDTO;
import dio.personapi.dto.response.MessageResponseDTO;
import dio.personapi.entity.Person;
import dio.personapi.exception.PersonNotFoundException;
import dio.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessageResponseDTO createPerson(@RequestBody @Validated PersonDTO personDTO){

        return personService.createPerson(personDTO);

    }

    @GetMapping
    public List<PersonDTO> listAll(){
       return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletedById(@PathVariable long id ) throws PersonNotFoundException {
        personService.delete(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {

        return personService.updateById(id, personDTO);


    }



}
