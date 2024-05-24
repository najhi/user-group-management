package com.paraminfo.assigment.services;

import com.paraminfo.assigment.entities.Group;
import com.paraminfo.assigment.repositories.GroupRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @Test
    public void testCreateGroup() {
        Group group = new Group();
        group.setName("Test Group");
        group.setDescription("Description of test group");

        Mockito.when(groupRepository.save(group)).thenReturn(group);

        Group created = groupService.createGroup(group);
        assertThat(created.getName()).isSameAs(group.getName());
    }

    @Test
    public void testGetAllGroups() {
        Group group = new Group();
        group.setName("Test Group");
        group.setDescription("Description of test group");

        List<Group> groups = Collections.singletonList(group);

        Mockito.when(groupRepository.findAll()).thenReturn(groups);

        List<Group> result1 = groupService.getAllGroups();
        List<Group> result2 = groupService.getAllGroups();

        assertThat(result1).isEqualTo(groups);
        assertThat(result2).isEqualTo(groups);

        // Verify that repository is called only once due to caching
        verify(groupRepository, times(1)).findAll();
    }
}