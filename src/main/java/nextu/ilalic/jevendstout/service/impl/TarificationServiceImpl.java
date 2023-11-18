package nextu.ilalic.jevendstout.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import nextu.ilalic.jevendstout.service.TarificationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class TarificationServiceImpl implements TarificationService {

    private final RestTemplate restTemplate;

    public TarificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTarif() {
        String url = "http://localhost:8091/anything?tarif=" + (new Random().nextInt(500) + 5);
        JsonNode result = restTemplate.getForObject(url, JsonNode.class);
        return Integer.parseInt(result.get("args").get("tarif").toString().replace("\"", ""));
    }
}
