package com.jayden.community.controller

import com.jayden.community.domain.Board
import com.jayden.community.repository.BoardRepository
import org.apache.coyote.Response
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.PagedModel.PageMetadata
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @PostMapping
    fun saveBoard(@RequestBody board: Board): ResponseEntity<Any> {
        boardRepository.save(board)
        return ResponseEntity("{}", HttpStatus.CREATED)
    }

    @PutMapping(value = ["/{id}"])
    fun updateBoard(@PathVariable(value = "id") id: Long, @RequestBody board: Board): ResponseEntity<Any> {
        val findBoard = boardRepository.getOne(id)
        findBoard.update(board)

        boardRepository.save(findBoard)

        return ResponseEntity("{}", HttpStatus.OK)
    }

    @DeleteMapping(value = ["/{id}"])
    fun deleteBoard(@PathVariable(value = "id") id: Long) : ResponseEntity<Any> {
        boardRepository.deleteById(id)
        return ResponseEntity("{}", HttpStatus.OK)
    }
}
