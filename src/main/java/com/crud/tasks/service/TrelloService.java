package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDto>fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card->simpleEmailService.send(new Mail(adminConfig.getAdminMail(),SUBJECT,"New card: " + trelloCardDto.getName()+" has been created on your trello account","")));
        return newCard;
    }
}
