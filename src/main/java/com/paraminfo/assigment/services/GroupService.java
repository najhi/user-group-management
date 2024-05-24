package com.paraminfo.assigment.services;

import com.paraminfo.assigment.entities.Group;
import com.paraminfo.assigment.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    private final CaffeineCache cache;

    @Autowired
    public GroupService(GroupRepository groupRepository, @Qualifier("groupCache") CaffeineCache cache) {
        this.groupRepository = groupRepository;
        this.cache = cache;
    }

    public Group createGroup(Group group) {
        cache.clear();
        return groupRepository.save(group);
    }

    @Cacheable(value = "groups")
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}