package by.htp.hvozdzeu.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity with id field
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = 5268474521104203193L;
	
	private Long id;

    protected Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
