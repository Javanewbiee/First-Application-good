package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test List",false));
        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("tes Board", "1",trelloListDtos));
        List<TrelloList>mappedTrellolists = new ArrayList<>();
        mappedTrellolists.add(new TrelloList("test list","1",false));
        List<TrelloBoard>mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","test board",mappedTrellolists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoard(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBeardDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto>trelloBoardDtoList = trelloFacade.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(trelloBoardDtoList);
        Assert.assertEquals(0,trelloBoardDtoList.size());
    }
    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto>trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1","test List",false));
        List<TrelloBoardDto>trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("test Board", "1",trelloListDtos));
        List<TrelloList>mappedTrellolists = new ArrayList<>();
        mappedTrellolists.add(new TrelloList("test list","1",false));
        List<TrelloBoard>mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1","test board",mappedTrellolists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoard(trelloBoardDtos)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBeardDto(anyList())).thenReturn(trelloBoardDtos);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto>trelloBoardDtoList = trelloFacade.fetchTrelloBoards();
        //Then
        Assert.assertNotNull(trelloBoardDtoList);
        Assert.assertEquals(1,trelloBoardDtoList.size());

        trelloBoardDtoList.forEach(trelloBoardDto -> {
            Assert.assertEquals("1",trelloBoardDto.getId());
            Assert.assertEquals("test Board",trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assert.assertEquals("1",trelloListDto.getId());
                Assert.assertEquals("test List",trelloListDto.getName());
                Assert.assertEquals(false,trelloListDto.isClosed());
            });
        });
    }
}
