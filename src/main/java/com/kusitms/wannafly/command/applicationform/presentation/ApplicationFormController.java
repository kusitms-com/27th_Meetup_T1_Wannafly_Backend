package com.kusitms.wannafly.command.applicationform.presentation;

import com.kusitms.wannafly.command.applicationform.application.ApplicationFormService;
import com.kusitms.wannafly.command.applicationform.application.ApplicationItemService;
import com.kusitms.wannafly.command.applicationform.dto.ApplicationFormCreateRequest;
import com.kusitms.wannafly.command.applicationform.dto.ApplicationFormUpdateRequest;
import com.kusitms.wannafly.command.applicationform.dto.ApplicationItemCreateRequest;
import com.kusitms.wannafly.command.applicationform.dto.FormStateRequest;
import com.kusitms.wannafly.command.auth.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationFormController {

    private final ApplicationFormService applicationFormService;
    private final ApplicationItemService applicationItemService;

    @PostMapping("/application-forms")
    public ResponseEntity<Void> createForm(@RequestBody ApplicationFormCreateRequest request,
                                           LoginMember loginMember) {
        Long formId = applicationFormService.createForm(loginMember, request);
        return ResponseEntity.created(URI.create("/application-forms/" + formId))
                .build();
    }

    @PatchMapping("/application-forms/{applicationFormId}")
    public ResponseEntity<Void> updateForm(@PathVariable Long applicationFormId,
                                           @RequestBody ApplicationFormUpdateRequest request,
                                           LoginMember loginMember) {
        applicationFormService.updateForm(applicationFormId, loginMember, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/application-forms/{applicationFormId}/items")
    public ResponseEntity<Void> addItem(@PathVariable Long applicationFormId,
                                        @RequestBody ApplicationItemCreateRequest request,
                                        LoginMember loginMember) {
        Long itemId = applicationItemService.addItem(applicationFormId, loginMember, request);
        return ResponseEntity.created(URI.create("/application-items/" + itemId))
                .build();
    }

    @DeleteMapping("/application-forms/{applicationFormId}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long applicationFormId,
                                           LoginMember loginMember) {
        applicationFormService.deleteForm(applicationFormId, loginMember);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/application-forms/{applicationFormId}/state")
    public ResponseEntity<Void> changeFormState(@PathVariable Long applicationFormId,
                                                @RequestBody FormStateRequest request,
                                                LoginMember loginMember) {
        applicationFormService.changeState(applicationFormId, loginMember, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/categories/{categoryId}/application-items/{applicationItemId}")
    public ResponseEntity<Void> registerCategory(@PathVariable Long categoryId,
                                                 @PathVariable Long applicationItemId,
                                                 LoginMember loginMember) {
        applicationItemService.registerCategory(categoryId, applicationItemId, loginMember);
        return ResponseEntity.noContent().build();
    }
}

