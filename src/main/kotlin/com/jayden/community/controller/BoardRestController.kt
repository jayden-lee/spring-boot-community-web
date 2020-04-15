package com.jayden.community.controller

import com.jayden.community.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.PagedModel.PageMetadata
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/boards"])
class BoardRestController(private val boardRepository: BoardRepository) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBoards(@PageableDefault pageable: Pageable): ResponseEntity<Any> {
        val boards = boardRepository.findAll(pageable)
        val pageMetadata = PageMetadata(pageable.pageSize.toLong(), boards.number.toLong(), boards.totalElements)

        val resources = PagedModel.wrap(boards.content, pageMetadata)
        resources.add(linkTo(methodOn(BoardRestController::class.java).getBoards(pageable)).withSelfRel())

        return ResponseEntity.ok(resources)
    }
}
