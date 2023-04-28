package com.resellerapp.service;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Offer> findAllBoughtItems(long loggedUserId) {
        Optional<User> byId = this.userRepository.findById(loggedUserId);
        List<Offer> boughtOffers = byId.get().getBoughtOffers();

        return boughtOffers;
    }
}
