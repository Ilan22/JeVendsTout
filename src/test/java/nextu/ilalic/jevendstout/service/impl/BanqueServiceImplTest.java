package nextu.ilalic.jevendstout.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BanqueServiceImplTest {
    @InjectMocks
    private BanqueServiceImpl banqueService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testPayer_Success() {
        // When
        boolean result = banqueService.payer();

        // Then
        assertTrue(result);
    }

    @Test
    public void testPayer_FailureAfter5Calls() {
        // Simulate 5 calls with "true" and then a call with "false"
        for (int i = 0; i < 4; i++) {
            banqueService.payer();
        }

        // When
        boolean result = banqueService.payer();

        // Then
        assertFalse(result);
    }

    private JsonNode createJsonResponse(String value) {
        ObjectNode jsonNode = JsonNodeFactory.instance.objectNode();
        jsonNode.putObject("args").put("resultat", value);
        return jsonNode;
    }
}
