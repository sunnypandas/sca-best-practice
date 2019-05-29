package com.acgnfuns.entity.codeless;

import com.alibaba.codeless.framework.autoconfigure.annotation.EnableCodeless;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zuoxiaolong
 */
@EnableCodeless
@Entity(name = "animation_episode")
@Data
public class AnimationEpisode {

    @Column(name="batch_date")
    private String batchDate   ;
    @Column(name="bangumi_url")
    private String bangumiUrl  ;
    @Column(name="episode_name")
    private String episodeName ;
    @Id
    @Column(name="episode_url")
    private String episodeUrl  ;
    @Column(name="playee_name")
    private String playeeName  ;
    @Column(name="playee_url")
    private String playeeUrl   ;

}