package project.bot.model.response;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tenders")
public class Tender  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tender_id")
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tender_site_id", nullable = false)
    private Site site;
    @Column(name = "tender_site_inner_id")
    private String siteInnerId;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "tender_initiator_id", nullable = false)
    private Initiator initiator;
    @Column(name = "tender_subject")
    private String subject;
    @Column(name = "tender_start_time")
    private LocalDateTime startTimestamp;
    @Column(name = "tender_end_time")
    private LocalDateTime endTimestamp;
    @NaturalId(mutable = true)
    @Column(name = "tender_url", nullable = false, unique = true)
    private String url;

    public Tender() {
    }

    public Tender(Integer id,
                  Site site,
                  String siteInnerId,
                  Initiator initiator,
                  String subject,
                  LocalDateTime startTimestamp,
                  LocalDateTime endTimestamp,
                  String url) {
        this.id = id;
        this.site = site;
        this.siteInnerId = siteInnerId;
        this.initiator = initiator;
        this.subject = subject;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.url = url;
    }

    public static class Builder {
        private Integer id;
        private Site site;
        private String siteInnerId;
        private Initiator initiator;
        private String subject;
        private LocalDateTime startTimestamp;
        private LocalDateTime endTimestamp;
        private String url;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withSite(Site site) {
            this.site = site;
            return this;
        }

        public Builder withSiteInnerId(String siteInnerId) {
            this.siteInnerId = siteInnerId;
            return this;
        }

        public Builder withInitiator(Initiator initiator) {
            this.initiator = initiator;
            return this;
        }

        public Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder withStartTimestamp(LocalDateTime startTimestamp) {
            this.startTimestamp = startTimestamp;
            return this;
        }

        public Builder withEndTimestamp(LocalDateTime endTimestamp) {
            this.endTimestamp = endTimestamp;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Tender build() {
            return new Tender(id, site, siteInnerId, initiator, subject, startTimestamp, endTimestamp, url);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getSiteInnerId() {
        return siteInnerId;
    }

    public void setSiteInnerId(String siteInnerId) {
        this.siteInnerId = siteInnerId;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
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
        if (!(o instanceof Tender)) return false;
        Tender tender = (Tender) o;
        return Objects.equals(id, tender.id) && Objects.equals(site, tender.site) && Objects.equals(siteInnerId, tender.siteInnerId) && Objects.equals(initiator, tender.initiator) && Objects.equals(subject, tender.subject) && Objects.equals(startTimestamp, tender.startTimestamp) && Objects.equals(endTimestamp, tender.endTimestamp) && Objects.equals(url, tender.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, siteInnerId, initiator, subject, startTimestamp, endTimestamp, url);
    }

    @Override
    public String toString() {
        return "Tender{" +
                "id=" + id +
                ", site=" + site +
                ", siteInnerId='" + siteInnerId + '\'' +
                ", initiator=" + initiator +
                ", subject='" + subject + '\'' +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                ", url='" + url + '\'' +
                '}';
    }
}