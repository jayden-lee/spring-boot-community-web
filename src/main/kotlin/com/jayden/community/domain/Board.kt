package com.jayden.community.domain

import com.jayden.community.domain.enum.BoardType
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class Board : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var title: String? = null

    @Column
    var subTitle: String? = null

    @Column
    var content: String? = null

    @Column
    @Enumerated(EnumType.STRING)
    var boardType: BoardType? = null

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column
    var updatedAt: LocalDateTime? = null

    @OneToOne
    var user: User? = null

    fun update(board: Board) {
        this.title = board.title
        this.subTitle = board.subTitle
        this.content = board.content
        this.boardType = board.boardType
        this.updatedAt = LocalDateTime.now()
    }
}
