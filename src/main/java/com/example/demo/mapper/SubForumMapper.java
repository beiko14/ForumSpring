package com.example.demo.mapper;

import com.example.demo.dto.SubForumDto;
import com.example.demo.model.Post;
import com.example.demo.model.SubForum;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// use https://mapstruct.org to map the properties

@Mapper(componentModel = "spring")
public interface SubForumMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subForum.getPosts()))")
    SubForumDto mapSubForumToDto(SubForum subForum);

    default Integer mapPosts(List<Post> numberOfPosts){
        return numberOfPosts.size();
    }

    //create the mappings from SubForumDto to SubForumEntity
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubForum mapDtoToSubForum(SubForumDto subForumDto);

}
