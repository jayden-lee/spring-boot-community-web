package com.jayden.community.service

import com.jayden.community.domain.Board
import com.jayden.community.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(val boardRepository: BoardRepository) {

    fun findBoardList(pageable: Pageable): Page<Board> {
        val pageNumber = if (pageable.pageNumber <= 0) 0 else pageable.pageNumber - 1
        val pageSize = if (pageable.pageSize <= 0) 10 else pageable.pageSize

        val pageRequest = PageRequest.of(pageNumber, pageSize)

        return boardRepository.findAll(pageRequest)
    }

    fun findBoardById(id: Long): Board {
        return boardRepository.findById(id).orElse(Board())
    }
}
