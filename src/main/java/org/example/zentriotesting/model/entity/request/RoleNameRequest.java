package org.example.zentriotesting.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.zentriotesting.model.entity.RoleName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleNameRequest {
    private RoleName roleName;
}
