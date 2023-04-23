package finki.mk.uiktBackend.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (getClass() != o.getClass()) {

            if (getClass().isInstance(o)) {
                return o.equals(this);
            }

            return false;
        }

        BaseEntity BaseEntity = (BaseEntity) o;
        return Objects.equals(id, BaseEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}