package com.jayden.community

import com.jayden.community.domain.Board
import com.jayden.community.domain.User
import com.jayden.community.domain.enum.BoardType
import com.jayden.community.repository.BoardRepository
import com.jayden.community.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataJpaTest
internal class JpaMappingTests {

    companion object {
        const val BOARD_TEST_TITLE = "테스트"
        const val EMAIL = "test@gmail.com"
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var boardRepository: BoardRepository

    @BeforeEach
    internal fun init() {
        var user = User().apply {
            this.name = "jayden"
            this.password = "test"
            this.email = EMAIL
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }

        var board = Board().apply {
            this.title = BOARD_TEST_TITLE
            this.subTitle = "서브 타이틀"
            this.content = "컨텐츠"
            this.boardType = BoardType.notice
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
            this.user = user
        }

        boardRepository.save(board)
    }

    @Test
    fun `엔티티 정상적으로 생성되었는지 테스트`() {
        val user = userRepository.findByEmail(EMAIL)

        assertThat(user?.name).isEqualTo("jayden")
        assertThat(user?.password).isEqualTo("test")
        assertThat(user?.email).isEqualTo(EMAIL)

        val board = boardRepository.findByUser(user!!)
        println(board)
    }
}
