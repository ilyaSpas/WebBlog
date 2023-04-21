package com.ilsy.school_dance.controllers;

import com.ilsy.school_dance.models.News;
import com.ilsy.school_dance.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private NewsRepository newsRepository;


    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("posts", news);
        return "blog";
    }



    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blogAdd";
    }

    @PostMapping("/blog/add")
    public String blogAddPost(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String date,
                              @RequestParam String text,
                              Model model) {
        News news = new News(title, anons, date, text);
        newsRepository.save(news);
        return "redirect:/blog";
    }



    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<News> news = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogDetails";
    }







    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional <News> news = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blogEdit"; //
    }

    @PostMapping("/blog/{id}/edit") // для отправки данных
    public String blogAddPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, // Аннотация получения значений из HTML формы
                                    @RequestParam String anons,
                                    @RequestParam String date,
                                    @RequestParam String text,
                                    Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        news.setTitle(title);
        news.setAnons(anons);
        news.setDate(date);
        news.setText(text);
        newsRepository.save(news);
        return "redirect:/blog";                  // переадресация на страничку блога
    }

    @PostMapping("/blog/{id}/remote")
    public String blogAddPostDelete(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);
        return "redirect:/blog";
    }

}
