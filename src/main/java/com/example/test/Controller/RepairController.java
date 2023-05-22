package com.example.test.Controller;

import com.example.test.Entity.Product;
import com.example.test.Entity.Repair;
import com.example.test.Reponsitory.ProductRepository;
import com.example.test.Reponsitory.RepairRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RepairController {
    private final RepairRepository repairRepository;
    private final ProductRepository productRepository;

    public RepairController(RepairRepository repairRepository,
                            ProductRepository productRepository) {
        this.repairRepository = repairRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/Repair")
    public String repair(Model model) {
        model.addAttribute("ItemRepairDtoList", repairRepository.findAll());
        return "Item Repair/View";
    }

    @GetMapping("/ItemRepairCreate")
    public String viewRepair(Model model) {
        model.addAttribute("repair", new Repair());
        return "Item Repair/Create";
    }

    @PostMapping("/ItemRepairCreate")
    public String createRepair(Model model, @ModelAttribute Repair repair,
                               @RequestParam Integer productId, RedirectAttributes redirectAttributes) {
        Product product = new Product();
        Optional<Product> optionalProduct= Optional.of(productRepository.findById(productId).orElse(product));
        try {
            if (optionalProduct.equals(product) ) {
                redirectAttributes.addFlashAttribute("fail", "Nhập sai mã sản phẩm!");
                return "redirect:/ItemRepairCreate";
            } else {
                repair.setProduct(productRepository.getOne(productId));
                repairRepository.save(repair);
                redirectAttributes.addFlashAttribute("success", "Thông tin bảo hành đã được thêm thành công!");
                return "redirect:/Repair";

            }
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("fail", "Nhập sai mã sản phẩm, vui lòng nhập lại!");
            return "redirect:/ItemRepairCreate";
        }
    }

}
