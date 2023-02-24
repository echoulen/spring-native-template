package nextdrive.springnativetemplate.data

import jakarta.persistence.*
import java.io.Serializable

@Table(name = "users")
@Entity
class User(
    @Column
    val name: String
) : BaseAutoIdEntity()

@MappedSuperclass
abstract class BaseAutoIdEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    override fun equals(other: Any?): Boolean {
        return other != null && other is BaseAutoIdEntity && other.id == (this.id ?: false)
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}