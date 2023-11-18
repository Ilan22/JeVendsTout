package nextu.ilalic.jevendstout.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TarificationServiceImplTest {
    @InjectMocks
    private TarificationServiceImpl tarificationService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetTarif() {
        // Given
        int expectedTarif = 100;

        JsonNode mockJsonNode = Mockito.mock(JsonNode.class);
        Mockito.when(mockJsonNode.get("args")).thenReturn(mockJsonNode);
        Mockito.when(mockJsonNode.get("tarif")).thenReturn(mockJsonNode);
        Mockito.when(mockJsonNode.toString()).thenReturn("\"100\"");

        // When
        Mockito.when(restTemplate.getForObject(any(String.class), ArgumentMatchers.eq(JsonNode.class))).thenReturn(mockJsonNode);

        int actualTarif = tarificationService.getTarif();

        // Then
        assertEquals(expectedTarif, actualTarif);
    }
}
