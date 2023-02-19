package nextdrive.springnativetemplate.controller

import io.swagger.v3.oas.annotations.tags.Tag
import nextdrive.springnativetemplate.data.User
import nextdrive.springnativetemplate.data.UserDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "hello api")
@RequestMapping("hello")
class HelloController(
    private val userDao: UserDao
) {
    @GetMapping
    suspend fun getHello(): User {
        return userDao.save(User(name = "hello user"))
    }
}
