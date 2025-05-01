package org.example.zentriotesting.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.zentriotesting.model.entity.RoleName;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequest {
    private RoleName roleName;
    private String email;
    private UUID boardId;
}
