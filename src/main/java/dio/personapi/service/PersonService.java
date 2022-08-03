package dio.personapi.service;

import dio.personapi.dto.request.PersonDTO;
import dio.personapi.dto.response.MessageResponseDTO;
import dio.personapi.entity.Person;
import dio.personapi.mapper.PersonMapper;
import dio.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){

        Person personToSave = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();

    }
}
