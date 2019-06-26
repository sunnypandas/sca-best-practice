package com.acgnfuns.entity.codeless;

import com.alibaba.codeless.framework.autoconfigure.annotation.EnableCodeless;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author zuoxiaolong
 */
@EnableCodeless
@Entity(name = "animation_bangumi")
@Data
public class AnimationBangumiPO {

    @Column(name="batch_date")
    private String batchDate    ;
    @Column(name="host")
    private String host          ;
    @Id
    @Column(name="url")
    private String url           ;
    @Column(name="img")
    private String img           ;
    @Column(name="image_urls")
    private String imageUrls    ;
    @Column(name="images")
    private String images        ;
    @Column(name="name")
    private String name          ;
    @Column(name="author_names")
    private String authorNames  ;
    @Column(name="actor_names")
    private String actorNames   ;
    @Column(name="director_names")
    private String directorNames;
    @Column(name="show_date")
    private String showDate     ;
    @Column(name="area_names")
    private String areaNames    ;
    @Column(name="language")
    private String language      ;
    @Column(name="status")
    private String status        ;
    @Column(name="type")
    private String type          ;
    @Column(name="introduction")
    private String introduction  ;
    @Column(name="update_time")
    private String updateTime   ;
    @Column(name="category")
    private String category      ;

}