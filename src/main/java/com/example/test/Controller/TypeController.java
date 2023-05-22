package com.example.test.Controller;

import com.example.test.Entity.Type;
import com.example.test.Reponsitory.TypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TypeController {
    private final TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping("/type")
    public String getRegister(Model model) {
        model.addAttribute("typeList",typeRepository.findAll() );
        return "Type/View";
    }
    @GetMapping("/typeCreate")
    public String typeCreate(Model model) {
        model.addAttribute("type",new Type() );
        return "Type/Create";
    }
    @GetMapping("/typeDelete")
    public String typeDelete(Model model, @RequestParam Integer id) {
        typeRepository.delete(typeRepository.getOne(id));
        return "redirect:/type";
    }

    @GetMapping("/searchType")
    public String searchType(Model model, @RequestParam String keyword) {
        model.addAttribute("typeList",typeRepository.findAllByNameContaining(keyword) );
        return "Type/View";
    }

    @PostMapping("/typeCreate")
    public String typeCreate1(Model model,@ModelAttribute Type type) {
        try {
            typeRepository.save(type);
            return "redirect:/type";
        }catch (Exception ex){
            model.addAttribute("mess","K thành công!");
            return "redirect:/typeCreate";
        }
    }

    @GetMapping("/typeEdit")
    public String typeEdit(Model model, @RequestParam Integer id) {
        model.addAttribute("type",typeRepository.getOne(id) );
        return "Type/Edit";
    }

    @PostMapping("/typeEdit")
    public String typeEdit1(Model model, @ModelAttribute Type type) {
        try {
            typeRepository.save(type);
            return "redirect:/type";
        }catch (Exception ex){
            return "redirect:/type";
        }

    }
}
