
package uz.silkStep.project.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @LastModifiedBy
    @JoinColumn(name = "updated_by")
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedBy
    @JoinColumn(name = "created_by", updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted = false;
}
// AbstractBaseDomain → serves as a base class for all entities in the application, providing common fields such as id, createdBy, createdAt, updatedBy, updatedAt, and deleted. 
//This promotes code reuse and consistency across different entities.