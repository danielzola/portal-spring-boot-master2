package id.go.dephub.hubla.sehati.service;

import id.go.dephub.hubla.sehati.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    @Value("${hubla.token.client.id}")
    private String clientId;

    @Value("${hubla.token.client.secret}")
    private String clientSecret;

    @Value("${hubla.token.base.url}")
    private String apisTokenUrl;

    private static final String GRANT_TYPE="grant_type";
    private static final String CLIENT_CREDENTIALS="client_credentials";


    private RestTemplate restTemplate;

    public TokenResponse getTokenResponse(){
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String,String> map=new LinkedMultiValueMap<String,String>();
        map.add(GRANT_TYPE,CLIENT_CREDENTIALS);
        headers.setBasicAuth(clientId,clientSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<TokenResponse> response=restTemplate.exchange(apisTokenUrl, HttpMethod.POST,request,TokenResponse.class);
        return response.getBody();
    }
}
