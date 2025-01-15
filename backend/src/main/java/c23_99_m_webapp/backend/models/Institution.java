package c23_99_m_webapp.backend.models;

import c23_99_m_webapp.backend.models.dtos.DataInstitutionRegistration;
import c23_99_m_webapp.backend.models.enums.EducationalLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "institutions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Institution {

    @Id
    private String cue;
    private String name;
    private String typeInstitution;
    @Enumerated(EnumType.STRING)
    private EducationalLevel educationalLevel;
    private String address;
    private String email;
    private String phone;
    private String website;
    @OneToMany(mappedBy = "institution")
    private List<User> users;
    @OneToOne
    @JoinColumn(name = "inventario_id", referencedColumnName = "id")
    private Inventory inventory;

    public Institution(DataInstitutionRegistration dataInstitutionRegistration) {
        this.cue = dataInstitutionRegistration.cue();
        this.name = dataInstitutionRegistration.name();
        this.typeInstitution = dataInstitutionRegistration.type_institution();
        this.educationalLevel = dataInstitutionRegistration.educational_level();
        this.address = dataInstitutionRegistration.address();
        this.email = dataInstitutionRegistration.email();
        this.phone = dataInstitutionRegistration.phone();
        this.website = dataInstitutionRegistration.website();
    }
}
