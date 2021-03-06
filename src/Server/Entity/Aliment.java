package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity

@FilterDefs({
        @FilterDef(name = "id", parameters = {@ParamDef(name = "id", type = "integer")}),
        @FilterDef(name = "name", parameters = {@ParamDef(name = "name", type = "string")})
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "name", condition = "name like '%' || :name || '%'"),
})

@Table(name = "Aliment")
public class Aliment extends AbstractEntity {

    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(unique = true, length = 32)
    private String name;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    private Set<Dish> dishes = new HashSet<>();

    @OneToMany(mappedBy = "affectedAliment", fetch = FetchType.EAGER)
    private Set<EatingDisorder> eatingDisorders = new HashSet<>();

    @ManyToMany(mappedBy = "supply", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Supplier> suppliers = new HashSet<>();

    public Aliment() {
    }

    public Aliment(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EatingDisorder> getEatingDisorders() {
        return eatingDisorders;
    }

    public void setEatingDisorders(Set<EatingDisorder> eatingDisorders) {
        this.eatingDisorders = eatingDisorders;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    protected void beforeDelete() {
        super.beforeDelete();
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    protected void beforeSave() {
        super.beforeSave();

        String correctName = this.getName();
        if(!validateString(correctName, "^[\\p{L} .'-]+$")) throw new IllegalArgumentException("Violated constraints on name field (No symbols allowed, no numbers allowed, max length = 32)");
        this.setName(nameCorrector(correctName));
    }
}
