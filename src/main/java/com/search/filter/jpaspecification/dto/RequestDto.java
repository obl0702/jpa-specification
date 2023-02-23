package com.search.filter.jpaspecification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;

    private GlobalOperator globalOperator;

    private PageRequestDto pageRequestDto;

    public enum GlobalOperator{
        AND, OR;
    }
}
