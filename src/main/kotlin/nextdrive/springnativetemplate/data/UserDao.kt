package nextdrive.springnativetemplate.data

import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long>
