package com.resellerapp.controller;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.dtos.OfferDto;
import com.resellerapp.service.AuthService;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final AuthService authService;
    private final LoggedUser loggedUser;
    private final OfferService offerService;
    private final UserService userService;

    public HomeController(AuthService authService, LoggedUser loggedUser, OfferService offerService, UserService userService) {
        this.authService = authService;
        this.loggedUser = loggedUser;
        this.offerService = offerService;
        this.userService = userService;
    }

    @ModelAttribute("offerDto")
    public OfferDto initOfferDto() {
        return new OfferDto();
    }

    @GetMapping("/")
    public String notLoggedInUser() {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        long loggedUserId = this.loggedUser.getId();


        if (!this.authService.isLoggedIn()) {
            return "redirect:/login";
        }
        List<Offer> myOffers = this.offerService.findAllOfferByUserId(loggedUserId);
        List<Offer> otherOffers = this.offerService.findAllOffersByUserIdNot(loggedUserId);

        List<Offer> boughtItems = this.userService.findAllBoughtItems(loggedUserId);

        model.addAttribute("username",loggedUser.getUsername());
        model.addAttribute("myOffers",myOffers);
        model.addAttribute("otherOffers",otherOffers);
        model.addAttribute("boughtItems",boughtItems);
        model.addAttribute("otherOffersCount",otherOffers.size());

        return "home";
    }
}
