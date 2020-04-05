package com.jayden.community.repository

import com.jayden.community.domain.Board
import com.jayden.community.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {

    fun findByUser(user: User): Board?
}
