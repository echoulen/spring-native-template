package nextdrive.springnativetemplate.controller

import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        return withContext(Dispatchers.IO) {
            userDao.save(User(name = "hello user"))
        }
    }
}
