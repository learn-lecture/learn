package org.study.admin.ui;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.admin.ui.query.UserStatsQueryRepository;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("result", userStatsQueryRepository.getDailyRegisterUserStats(7));

        return modelAndView;
    }

}
