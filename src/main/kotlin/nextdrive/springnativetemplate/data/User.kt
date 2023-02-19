package nextdrive.springnativetemplate.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "users")
class User(
    @Id
    var id: Long? = null,
    val name: String
)
