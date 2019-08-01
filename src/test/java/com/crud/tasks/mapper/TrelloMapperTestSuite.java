package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTestSuite {

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto testList = new TrelloListDto("1","Test List",false);
        List<TrelloListDto>dtoList = new ArrayList<>();
        dtoList.add(testList);
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        List<TrelloList>result = trelloMapper.mapToList(dtoList);
        //Then
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto testList = new TrelloListDto("1","Test List",true);
        List<TrelloListDto>dtoList = new ArrayList<>();
        dtoList.add(testList);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Test Board", "1",dtoList);
        List<TrelloBoardDto>dtoBoardList = new ArrayList<>();
        dtoBoardList.add(trelloBoardDto);
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        List<TrelloBoard>result = trelloMapper.mapToBoard(dtoBoardList);
        //Then
        Assert.assertEquals(1,result.size());
    }
    @Test
    public void testMapToBoardDto() {
        //Given
        TrelloList testList = new TrelloList("tes list","1",false);
        List<TrelloList>list = new ArrayList<>();
        list.add(testList);
        TrelloBoard trelloBoard = new TrelloBoard("1","test board",list);
        List<TrelloBoard>boardsList = new ArrayList<>();
        boardsList.add(trelloBoard);
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        List<TrelloBoardDto>result = trelloMapper.mapToBeardDto(boardsList);
        //Then
        Assert.assertEquals(1,result.size());
    }
    @Test
    public void testMapToListDto() {
        //Given
        TrelloList testList = new TrelloList("1","Test List",false);
        List<TrelloList>list = new ArrayList<>();
        list.add(testList);
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        List<TrelloListDto>result = trelloMapper.mapToListDto(list);
        //Then
        Assert.assertEquals(1,result.size());
    }
    @Test
    public void testMaptoTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card","test","1","T1");
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        TrelloCardDto result = trelloMapper.mapToTrelloCardDto(trelloCard);
        //Then
        Assert.assertEquals(trelloCard.getName(),result.getName());
    }
    @Test
    public void testMapToTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test cardDto","test", "1","TFO1");
        TrelloMapper trelloMapper = new TrelloMapper();
        //When
        TrelloCard result = trelloMapper.mapToTrelloCard(trelloCardDto);
        //Then
        Assert.assertEquals(trelloCardDto.getName(),result.getName());
    }
}
