package org.example.zentriotesting.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.request.BoardRequest;
import org.example.zentriotesting.model.entity.request.MemberRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.model.entity.response.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Member Controller")
public class MemberController {

    @Operation(summary = "Invite member by email")
    @PostMapping()
    public ResponseEntity<ApiResponse<Member>> createBoard(@RequestBody MemberRequest memberRequest){
        ApiResponse<Member> response = ApiResponse.<Member> builder()
                .success(true)
                .message("Created board successfully!")
                .status(HttpStatus.CREATED)
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
