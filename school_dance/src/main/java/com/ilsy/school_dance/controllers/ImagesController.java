package com.ilsy.school_dance.controllers;

import com.ilsy.school_dance.models.ImagesURL;
import com.ilsy.school_dance.models.News;
import com.ilsy.school_dance.repo.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImagesController {
    @Autowired
    private ImagesRepository imagesRepository;

    @GetMapping("/image")
    public String image(Model model) {
        Iterable<ImagesURL> image = imagesRepository.findAll();
        model.addAttribute("images", image);
        return "image";
    }

    @PostMapping("/image/add")
    public String addImage(@RequestParam String imageURL) {
        ImagesURL imagesURL = new ImagesURL(imageURL);
        imagesRepository.save(imagesURL);
        return "redirect:/image";
    }

    @PostMapping("/image/{id}/remote")
    public String removeImage(@PathVariable(value = "id") long id) {
        ImagesURL imagesURL = imagesRepository.findById(id).orElseThrow();
        imagesRepository.delete(imagesURL);
        return "redirect:/image";
    }
}
