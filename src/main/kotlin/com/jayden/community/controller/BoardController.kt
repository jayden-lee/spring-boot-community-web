package com.jayden.community.controller

import com.jayden.community.service.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping(value = ["/board"])
class BoardController(val boardService: BoardService) {

    @GetMapping(value = ["", "/"])
    fun board(@RequestParam(value = "id", defaultValue = "0") id: Long, model: Model): String {
        model.addAttribute("board", boardService.findBoardById(id))
        return "/board/form"
    }

    @GetMapping(value = ["/list"])
    fun list(@PageableDefault pageable: Pageable, model: Model): String {
        model.addAttribute("boardList", boardService.findBoardList(pageable))
        return "/board/list"
    }
}
