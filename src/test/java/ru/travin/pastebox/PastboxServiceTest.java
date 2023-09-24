package ru.travin.pastebox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.travin.pastebox.api.responce.PasteboxResponce;
import ru.travin.pastebox.exception.NotFoundEntityException;
import ru.travin.pastebox.repository.PasteboxEntity;
import ru.travin.pastebox.repository.PasteboxRepository;
import ru.travin.pastebox.service.PasteboxService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PastboxServiceTest {

    @Autowired
    private PasteboxService pasteboxService;

    @MockBean
    private PasteboxRepository pasteboxRepository;
    @Test
    public void notExistHash(){
        when(pasteboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteboxService.getByHash("Привет как дела?"));
    }
    @Test
    public void getExistHash(){
        PasteboxEntity entity = new PasteboxEntity();
        entity.setHash("1");
        entity.setData("11");
        entity.setPublic(true);

        when(pasteboxRepository.getByHash("1")).thenReturn(entity);

        PasteboxResponce expected = new PasteboxResponce("11", true);
        PasteboxResponce actual = pasteboxService.getByHash("1");

        assertEquals(expected, actual);
    }

}
