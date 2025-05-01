package org.example.zentriotesting.model.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.RoleName;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private UUID memberId;
    private RoleName roleName;
    private AppUser userId;
    private Board boardId;
}
