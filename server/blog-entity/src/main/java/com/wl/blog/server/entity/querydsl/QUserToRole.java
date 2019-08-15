package com.wl.blog.server.entity.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.wl.blog.server.entity.UserToRole;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QUserToRole is a Querydsl query type for UserToRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserToRole extends EntityPathBase<UserToRole> {

    private static final long serialVersionUID = 373866652L;

    public static final QUserToRole userToRole = new QUserToRole("userToRole");

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

    public final StringPath roleId = createString("roleId");

    public final StringPath userId = createString("userId");

    public QUserToRole(String variable) {
        super(UserToRole.class, forVariable(variable));
    }

    public QUserToRole(Path<? extends UserToRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserToRole(PathMetadata metadata) {
        super(UserToRole.class, metadata);
    }

}

