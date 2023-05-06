package project.bot.model.response;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tender_initiators")
public class Initiator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tender_initiator_id")
    private Integer id;
    @NaturalId(mutable = true)
    @Column(name = "tender_initiator_name", nullable = false, unique = true)
    private String name;
    @Column(name = "tender_initiator_contact_name")
    private String contactName;
    @Column(name = "tender_initiator_contact_phone")
    private String contactPhone;
    @OneToMany(mappedBy = "initiator", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tender> tenders = new HashSet<>();

    public void addTender(Tender tender) {
        tenders.add(tender);
        tender.setInitiator(this);
    }

    public Initiator() {
    }

    public Initiator(Integer id, String name, String contactName, String contactPhone) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String contactName;
        private String contactPhone;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withContactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public Builder withContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public Initiator build() {
            return new Initiator(id, name, contactName, contactPhone);
        }
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Initiator)) return false;
        Initiator initiator = (Initiator) o;
        return Objects.equals(name, initiator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Initiator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                '}';
    }
}
