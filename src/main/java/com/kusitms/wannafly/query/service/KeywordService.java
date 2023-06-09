package com.kusitms.wannafly.query.service;

import com.kusitms.wannafly.command.auth.LoginMember;
import com.kusitms.wannafly.query.dto.KeywordItemResponse;
import com.kusitms.wannafly.query.repository.ApplicationItemQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordService {
    private final ApplicationItemQueryRepository applicationItemQueryRepository;

    public List<KeywordItemResponse> findByKeyword(String keyword, LoginMember loginMember) {
        if (keyword.isBlank()) {
            return Collections.emptyList();
        }
        Long memberId = loginMember.id();
        return applicationItemQueryRepository.findItemsByKeyword(memberId, keyword).stream()
                .map(KeywordItemResponse::from)
                .toList();
    }
}
