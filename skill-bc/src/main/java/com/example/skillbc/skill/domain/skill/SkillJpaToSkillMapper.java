package com.example.skillbc.skill.domain.skill;

import com.example.skillbc.skill.domain.common.Identity;
import com.example.skillbc.skill.application.skill.interfaces.ISkillJpaToSkillMapper;
import com.example.skillbc.skill.infrastructure.skill.SkillJpa;
import org.springframework.stereotype.Component;

@Component

public class SkillJpaToSkillMapper implements ISkillJpaToSkillMapper {

    @Override
    public Skill map(SkillJpa skillJpa) {

        Identity identity = new Identity(skillJpa.getId());

        return Skill.skillOf(identity,
                skillJpa.getDescription(), skillJpa.getCategory().getId());
    }
}
