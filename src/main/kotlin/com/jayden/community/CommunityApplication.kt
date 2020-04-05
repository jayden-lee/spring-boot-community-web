package com.jayden.community

import com.jayden.community.domain.Board
import com.jayden.community.domain.User
import com.jayden.community.domain.enum.BoardType
import com.jayden.community.repository.BoardRepository
import com.jayden.community.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import java.time.LocalDateTime
import java.util.stream.IntStream

@SpringBootApplication
class CommunityApplication {

    @Bean
    @Profile("!test")
    fun runner(userRepository: UserRepository, boardRepository: BoardRepository): CommandLineRunner {
        return CommandLineRunner {
            val user = User().apply {
                this.name = "jayden"
                this.password = "test"
                this.email = "test@gmail.com"
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }
            userRepository.save(user)

            IntStream.rangeClosed(1, 200).forEach {
                val board = Board().apply {
                    this.title = "게시글 #${it}"
                    this.subTitle = "서브 타이틀 @${it}"
                    this.content = "컨텐츠"
                    this.boardType = BoardType.free
                    this.createdAt = LocalDateTime.now()
                    this.updatedAt = LocalDateTime.now()
                    this.user = user
                }
                boardRepository.save(board)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CommunityApplication>(*args)
}
