package com.zack.cloudpicturebackend.model.dto.user;


import com.zack.cloudpicturebackend.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = -9054667406157050696L;

    private  Long id;

    private String userAccount;

    private String userName;

    private String userRole;

    private String userProfile;
}
