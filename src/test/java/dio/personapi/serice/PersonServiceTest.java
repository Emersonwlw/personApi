package dio.personapi.serice;

import dio.personapi.dto.request.PersonDTO;
import dio.personapi.dto.response.MessageResponseDTO;
import dio.personapi.entity.Person;
import dio.personapi.repository.PersonRepository;
import dio.personapi.service.PersonService;
import dio.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static dio.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();

        Person expectedSavedPerson =  createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccesMessage = creatExpectedMessageResponse(expectedSavedPerson.getId());

        MessageResponseDTO succesMessage =  personService.createPerson(personDTO);

        assertEquals(expectedSuccesMessage, succesMessage);
    }

    private MessageResponseDTO creatExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with id " + id)
                .build();
    }
}
