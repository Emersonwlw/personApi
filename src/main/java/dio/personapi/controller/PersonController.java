package dio.personapi.controller;

import dio.personapi.dto.request.PersonDTO;
import dio.personapi.dto.response.MessageResponseDTO;
import dio.personapi.entity.Person;
import dio.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


}
