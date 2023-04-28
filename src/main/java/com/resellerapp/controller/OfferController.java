package com.resellerapp.controller;

import com.resellerapp.model.entity.dtos.CreateOfferDto;
import com.resellerapp.service.OfferService;
import com.resellerapp.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OfferController {

    private final OfferService offerService;

    private final LoggedUser loggedUser;

    public OfferController(OfferService offerService, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("createOfferDto")
    public CreateOfferDto initOfferDto(){
        return new CreateOfferDto();
    }

    @GetMapping("/offer/add")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/offer/add")
    public String addOffer(@Valid CreateOfferDto createOfferDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createOfferDto", createOfferDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.createOfferDto", bindingResult);
            return "redirect:/offer/add";
        }

        this.offerService.saveOffer(createOfferDto);

        return "redirect:/home";
    }
    @GetMapping("/offer/remove/{id}")
        public String removeOffer(@PathVariable Long id){

        offerService.removeOfferById(id);

        return "redirect:/home";
    }
    @GetMapping("/offer/buy/{offerId}")

    public String buyOffer(@PathVariable Long offerId){
        long loggedUserId = this.loggedUser.getId();

        offerService.buyOfferById(offerId,loggedUserId);
        return "redirect:/home";
    }
}
