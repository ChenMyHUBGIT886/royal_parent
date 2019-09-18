package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Word;
import com.bbs.service.WordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordService wordService;

    /**
     * 查询所有敏感词
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByPage.do")
    public ModelAndView findByPage(@RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum,
                                   @RequestParam(name = "pageSize", required = true, defaultValue = "5") int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Word> wordList = wordService.findByPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(wordList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("WordPage");
        return mv;
    }
}
