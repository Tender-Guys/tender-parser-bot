package project.bot.model.response;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tender_sites")
public class Site implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tender_site_id")
    private Integer id;
    @Column(name = "tender_site_name")
    private String name;
    @NaturalId(mutable = true)
    @Column(name = "tender_site_home_url", nullable = false, unique = true)
    private String url;
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tender> tenders = new HashSet<>();

    public void addTender(Tender tender) {
        tenders.add(tender);
        tender.setSite(this);
    }

    public Site() {
    }

    public Site(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String url;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Site build() {
            return new Site(id, name, url);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return Objects.equals(url, site.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
