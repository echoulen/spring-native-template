package nextdrive.springnativetemplate.data

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserDao : CoroutineCrudRepository<User, Long>
