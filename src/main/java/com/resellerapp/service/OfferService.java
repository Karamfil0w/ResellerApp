package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.entity.dtos.CreateOfferDto;
import com.resellerapp.model.entity.dtos.OfferDto;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.session.LoggedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ConditionRepository conditionRepository;
    private final LoggedUser loggedUser;
    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, ConditionRepository conditionRepository, LoggedUser loggedUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public List<Offer> findAllOfferByUserId(long loggedUserId) {
        List<Offer> byUserId = this.offerRepository.findByUserId(loggedUserId);

        return byUserId;
    }

    public List<Offer> findAllOffersByUserIdNot(long loggedUserId){
        List<Offer> byUserIdNot = this.offerRepository.findByUserIdNot(loggedUserId);

        return byUserIdNot;

    }

    public void saveOffer(CreateOfferDto createOfferDto) {
        Offer offerToSave = new Offer();

        offerToSave.setDescription(createOfferDto.getDescription());

        offerToSave.setPrice(createOfferDto.getPrice());

        Optional<Condition> byConditionName = conditionRepository.findByConditionName(createOfferDto.getCondition());
        offerToSave.setCondition(byConditionName.get());


        Optional<User> byId = this.userRepository.findById(loggedUser.getId());

        offerToSave.setUser(byId.get());

        this.offerRepository.save(offerToSave);
    }

    public void removeOfferById(Long id) {

        Optional<Offer> byId = this.offerRepository.findById(id);
        this.offerRepository.delete(byId.get());

    }

    @Transactional
    public void buyOfferById(Long id, Long loggedUserId) {
        Optional<User> currentLoggedUser = this.userRepository.findById(loggedUserId);

        List<Offer> boughtOffers = currentLoggedUser.get().getBoughtOffers();

        Optional<Offer> offerToBuy = this.offerRepository.findById(id);

        offerToBuy.get().setUser(null);

        boughtOffers.add(offerToBuy.get());

        userRepository.save(currentLoggedUser.get());

    }
}
