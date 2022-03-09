package com.project.extracurricularservice.entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class SpringSecurityAuditorAware<U> implements AuditorAware<String> {
Auditable<U> auditable ;
@Override
public Optional<String> getCurrentAuditor() {

//return Optional.empty();
return Optional.ofNullable("apoorva").filter(s-> !s.isEmpty());
}

}