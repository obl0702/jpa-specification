package com.search.filter.jpaspecification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;
}
