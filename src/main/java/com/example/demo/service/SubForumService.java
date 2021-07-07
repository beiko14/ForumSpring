package com.example.demo.service;

import com.example.demo.dto.SubForumDto;
import com.example.demo.model.SubForum;
import com.example.demo.repository.SubForumRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubForumService {

    private final SubForumRepository subForumRepository;

    @Transactional
    public SubForumDto save(SubForumDto subForumDto){
        SubForum save = subForumRepository.save(mapSubForumDto(subForumDto));
        subForumDto.setId(save.getId());
        return subForumDto;
    }

    private SubForum mapSubForumDto(SubForumDto subForumDto){
        return SubForum.builder().name(subForumDto.getName())
                .description(subForumDto.getDescription())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SubForumDto> getAll() {
        return subForumRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SubForumDto mapToDto(SubForum subForum) {
        return SubForumDto.builder().name(subForum.getName())
                .id(subForum.getId())
                .numberOfPosts(subForum.getPosts().size())
                .build();
    }
}
