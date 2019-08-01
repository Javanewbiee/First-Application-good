package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloList {

    private String name;
    private String id;
    private boolean isClosed;

}
