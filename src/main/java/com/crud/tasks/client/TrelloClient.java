package com.crud.tasks.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String username;

    public List<TrelloBoardDto> getTrelloBoards() {
        URI builtUrl = buildAdress();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(builtUrl, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }
    private URI buildAdress() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint+"/members/"+username+"/boards")
                .queryParam("key",trelloAppKey)
                .queryParam("token",trelloToken)
                .queryParam("username",username)
                .queryParam("fields","name,id")
                .queryParam("lists","all").build().encode().toUri();
        return url;
    }
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint+"/cards")
                .queryParam("key",trelloAppKey)
                .queryParam("token",trelloToken)
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getListId()).build().encode().toUri();

        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }
}
