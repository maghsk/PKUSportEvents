package org.pkuse2020grp4.pkusporteventsbackend.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "article")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int articleId;

    private int authorId;

    private Date releaseDate;

    private String title;

    private String content;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "r_article_tag",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonIgnore
    private List<Tag> tags;

    @JSONField(serialize = false)
    public List<Tag> getTags() {
        return tags;
    }

    @JSONField(name = "tag_ids")
    public List<Integer> getTagIds() {
        ImmutableList.Builder<Integer> builder = new ImmutableList.Builder<>();
        for (Tag tag: tags) {
            builder.add(tag.getTagId());
        }
        return builder.build();
    }
}
