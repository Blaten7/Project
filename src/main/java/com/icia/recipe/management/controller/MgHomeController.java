package com.icia.recipe.management.controller;

import com.icia.recipe.management.service.BoardService;
import com.icia.recipe.management.service.CommonService;
import com.icia.recipe.management.service.InvenService;
import com.icia.recipe.management.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MgHomeController {

    @Autowired
    BoardService bSer;

    @Autowired
    InvenService iSer;

    @Autowired
    SearchService sSer;

    @GetMapping("/main")
    public String Sales(HttpSession session, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/main";
    }

    @GetMapping("/delivery")
    public String delivery(HttpSession session, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/delivery";
    }

    @GetMapping("/inventory")
    public String inven(HttpSession session, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/inventory";
    }

    @GetMapping("/service")
    public String serviceCenter(HttpSession session, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/service";
    }

    @GetMapping("/homepageR")
    public String homepageR(HttpSession session, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/homepageR";
    }
    @GetMapping("/homepageC")
    public String homepageC(HttpSession session, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        String m_id=userDetails.getUsername();
        model.addAttribute("m_id",m_id);
        return "management/homepageC";
    }
    @GetMapping("/homepageT")
    public String homepageT(HttpSession session, Model model) {
        return "management/homepageF";
    }
    @GetMapping("/test")
    public String test(HttpSession session, Model model) {
        return "management/test";
    }
    @GetMapping("/common/search")
    public String search(Model model, @RequestParam("Keyword") String Keyword) {
        List<?> searchList = sSer.getSearchListAll(Keyword);
        model.addAttribute("searchList", searchList);
        return "management/search";
    }



}
