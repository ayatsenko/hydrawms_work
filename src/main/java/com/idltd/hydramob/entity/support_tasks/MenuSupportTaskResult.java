package com.idltd.hydramob.entity.support_tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MenuSupportTaskResult {
    @Id
    @Column(name = "RN", nullable = false)
    public Long rn;

    @Column(name = "CANCEL_COUNT", nullable = false)
    public Long cancel_count;

    @Column(name = "CANCEL_PERSENT", nullable = false)
    public Long cancel_persent;

    @Column(name = "CANCEL_COLOR", nullable = false)
    public String cancel_color;


    @Column(name = "NEW_COUNT", nullable = false)
    public Long new_count;

    @Column(name = "NEW_PERSENT", nullable = false)
    public Long new_persent;

    @Column(name = "NEW_COLOR", nullable = false)
    public String new_color;


    @Column(name = "WORK_COUNT", nullable = false)
    public Long work_count;

    @Column(name = "WORK_PERSENT", nullable = false)
    public Long work_persent;

    @Column(name = "WORK_COLOR", nullable = false)
    public String work_color;


    @Column(name = "RELEASE_COUNT", nullable = false)
    public Long release_count;

    @Column(name = "RELEASE_PERSENT", nullable = false)
    public Long release_persent;

    @Column(name = "RELEASE_COLOR", nullable = false)
    public String release_color;


    @Column(name = "TEST_COUNT", nullable = false)
    public Long test_count;

    @Column(name = "TEST_PERSENT", nullable = false)
    public Long test_persent;

    @Column(name = "TEST_COLOR", nullable = false)
    public String test_color;


    @Column(name = "DONE_COUNT", nullable = false)
    public Long done_count;

    @Column(name = "DONE_PERSENT", nullable = false)
    public Long done_persent;

    @Column(name = "DONE_COLOR", nullable = false)
    public String done_color;


    @Column(name = "ALL_COUNT", nullable = false)
    public Long all_count;
}
