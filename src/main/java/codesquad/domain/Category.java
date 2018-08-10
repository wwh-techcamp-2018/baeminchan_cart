package codesquad.domain;

import antlr.LexerSharedInputState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import sun.util.resources.cldr.teo.CalendarData_teo_KE;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

}
