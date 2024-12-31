package org.study.admin.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.admin.ui.dto.GetUserTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableRequestDto;
import org.study.admin.ui.dto.posts.GetPostTableResponseDto;
import org.study.admin.ui.dto.users.GetUserTableListResponseDto;
import org.study.admin.ui.dto.users.GetUserTableResponseDto;
import org.study.admin.ui.query.AdminTableQueryRepository;
import org.study.admin.ui.query.UserStatsQueryRepository;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("result", userStatsQueryRepository.getDailyRegisterUserStats(7));

        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");

        GetUserTableListResponseDto<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableData(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("userList", result.getTotalData());
        modelAndView.addObject("totalCount", result.getTotalCount());

        return modelAndView;
    }

    @GetMapping("/posts")
    public ModelAndView posts(GetPostTableRequestDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("posts");

        GetUserTableListResponseDto<GetPostTableResponseDto> result = adminTableQueryRepository.getPostTableData(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("postList", result.getTotalData());
        modelAndView.addObject("totalCount", result.getTotalCount());

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
