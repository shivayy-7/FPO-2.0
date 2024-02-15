package com.fpo.web.entities;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_milestone_collector_parent")
public class MilestoneCollectorParent extends Auditable<User> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9055727789813092677L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcp_id", nullable = false)
    private Long id;


    @Column(name = "mcp_code")
    private String mcpCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inst_id")
    private FarmerInstallment inst;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="forwarded_id")
    private StageForwardedRule stageForwardedRule;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mcp")
    private List<MilestoneCollectorChild> mileStoneChild;

}