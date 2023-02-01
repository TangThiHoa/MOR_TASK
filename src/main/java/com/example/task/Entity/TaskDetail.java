package com.example.task.Entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "task_detail")
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;


    @Column(name = "link_project")
    private String linkProject;

    @Column(name = "link_task")
    private String linkTask;
    @Column(name = "description_task")
    private String description;

    private String note;

    private String point;
    @Column(name = "estimate_time")
    private int estimateTime;
    @Column(name = "real_time")
    private int realTime;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "update_by")
    private String updateBy;

}
