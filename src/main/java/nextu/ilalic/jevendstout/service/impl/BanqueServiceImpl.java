package nextu.ilalic.jevendstout.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import nextu.ilalic.jevendstout.service.BanqueService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BanqueServiceImpl implements BanqueService {

    private int nbCall = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean payer() {
        String attendu = "true";
        nbCall++;
        if (nbCall == 5){
            attendu = "false";
            nbCall = 0;
        }
        String url = "http://localhost:8092/anything?resultat=" + attendu;
        RestTemplate restTemplate = new RestTemplate();
        JsonNode result = restTemplate.getForObject(url, JsonNode.class);
        return Boolean.parseBoolean(result.get("args").get("resultat").toString().replace("\"", ""));
    }
}
