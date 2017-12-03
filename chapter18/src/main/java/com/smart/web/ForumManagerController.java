package com.smart.web;

import com.smart.domain.Board;
import com.smart.service.ForumService;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by shliangyan on 2017/11/8.
 */
@Controller
public class ForumManagerController extends BaseController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String listAllBoards() {
//        List<Board> boards = forumService
        return null;
    }

    @RequestMapping(value = "/forum/addBoard", method = RequestMethod.POST)
    public String addBoard(Board board) {
        return "/addBoardSuccess";
    }

    public ModelAndView setBoardManagerPage() {
        ModelAndView view = new ModelAndView();
        return view;
    }
}
