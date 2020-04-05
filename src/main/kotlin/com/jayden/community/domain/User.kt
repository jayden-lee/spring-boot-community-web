package com.jayden.community.domain

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
class User : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String? = null

    @Column
    var password: String ?= null

    @Column
    var email: String ?= null

    @Column
    var createdAt: LocalDateTime? = null

    @Column
    var updatedAt: LocalDateTime? = null

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    var board: Board? = null
}
