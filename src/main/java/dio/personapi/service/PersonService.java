package dio.personapi.service;

import dio.personapi.dto.request.PersonDTO;
import dio.personapi.dto.response.MessageResponseDTO;
import dio.personapi.entity.Person;
import dio.personapi.exception.PersonNotFoundException;
import dio.personapi.mapper.PersonMapper;
import dio.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {

        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {


      Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }


    public void delete(long id) throws PersonNotFoundException {

        verifyIfExists(id);

        personRepository.deleteById(id);
    }
}
