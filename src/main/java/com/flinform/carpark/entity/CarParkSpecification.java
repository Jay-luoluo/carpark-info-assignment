package com.flinform.carpark.entity;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CarParkSpecification implements Specification<CarPark> {

    private String freeParking;
    private String nightParking;
    private double gantryHeight;

    public CarParkSpecification(String freeParking, String nightParking, double gantryHeight) {
        this.freeParking = freeParking;
        this.nightParking = nightParking;
        this.gantryHeight = gantryHeight;
    }

    @Override
    public Predicate toPredicate(Root<CarPark> root, javax.persistence.criteria.CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(freeParking) ) {
            predicates.add(cb.equal(root.get("freeParking"), freeParking));
        }
        if (StringUtils.isNotBlank(nightParking)) {
            predicates.add(cb.equal(root.get("nightParking"), nightParking));
        }
        if (gantryHeight >= 0) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("gantryHeight"), gantryHeight));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}

