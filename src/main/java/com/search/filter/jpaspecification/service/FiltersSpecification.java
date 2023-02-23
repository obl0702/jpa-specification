package com.search.filter.jpaspecification.service;

import com.search.filter.jpaspecification.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for(SearchRequestDto requestDto : searchRequestDtos){
                Predicate p = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                predicates.add(p);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
