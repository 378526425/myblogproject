package com.wl.blog.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRoleToPermission is a Querydsl query type for RoleToPermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleToPermission extends EntityPathBase<RoleToPermission> {

    private static final long serialVersionUID = 1245429152L;

    public static final QRoleToPermission roleToPermission = new QRoleToPermission("roleToPermission");

    public final com.wl.common.entity.QBaseObject _super = new com.wl.common.entity.QBaseObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> modifiedTime = _super.modifiedTime;

    //inherited
    public final StringPath modifier = _super.modifier;

    public final StringPath permissionId = createString("permissionId");

    public final StringPath roleId = createString("roleId");

    public QRoleToPermission(String variable) {
        super(RoleToPermission.class, forVariable(variable));
    }

    public QRoleToPermission(Path<? extends RoleToPermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoleToPermission(PathMetadata metadata) {
        super(RoleToPermission.class, metadata);
    }

}

