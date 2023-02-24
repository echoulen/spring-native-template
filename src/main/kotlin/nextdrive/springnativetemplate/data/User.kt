package nextdrive.springnativetemplate.data

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "users")
class User(
    @Id
    var id: Long? = null,
    val name: String
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return other != null && other is User && other.id == (this.id ?: false)
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
