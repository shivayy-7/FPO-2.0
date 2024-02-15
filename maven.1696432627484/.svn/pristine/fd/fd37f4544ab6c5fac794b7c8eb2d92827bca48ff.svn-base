package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_milestone_collector_child")
public class MilestoneCollectorChild extends Auditable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcc_id", nullable = false)
    private Long id;


    @Column(name = "mcc_code")
    private String mccCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcp_id")
    private MilestoneCollectorParent mcp;


    @Column(name = "mcp_value")
    private String mcpValue;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "milstone_id")
    private Milstone milstone;

    @Transient
    private MultipartFile mcpMultipartFile;

    @Transient
    private String mcpMultipartFileWeb;

}