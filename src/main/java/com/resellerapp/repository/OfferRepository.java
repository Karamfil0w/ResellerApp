package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.dtos.OfferDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findByUserId(long loggedUserId);

    List<Offer> findByUserIdNot(long loggedUserId);


}
