package com.example.demo.service;


import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.SubForumNotFoundException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Post;
import com.example.demo.model.SubForum;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.SubForumRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubForumRepository subForumRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        SubForum subForum = subForumRepository.findByName(postRequest.getSubForumName())
                .orElseThrow(() -> new SubForumNotFoundException(postRequest.getSubForumName()));

        postRepository.save(postMapper.map(postRequest, subForum, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));

        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubForum(Long subForumId){
        SubForum subForum = subForumRepository.findById(subForumId)
                .orElseThrow(() -> new SubForumNotFoundException(subForumId.toString()));
        List<Post> posts = postRepository.findAllBySubForum(subForum);

        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

}





