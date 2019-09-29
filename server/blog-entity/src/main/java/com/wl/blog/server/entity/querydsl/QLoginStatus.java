package com.wl.blog.server.entity.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.wl.blog.server.entity.LoginStatus;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QLoginStatus is a Querydsl query type for LoginStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoginStatus extends EntityPathBase<LoginStatus> {

    private static final long serialVersionUID = 1487979867L;

    public static final QLoginStatus loginStatus = new QLoginStatus("loginStatus");

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

    public final StringPath sessionId = createString("sessionId");

    public final StringPath userName = createString("userName");

    public QLoginStatus(String variable) {
        super(LoginStatus.class, forVariable(variable));
    }

    public QLoginStatus(Path<? extends LoginStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginStatus(PathMetadata metadata) {
        super(LoginStatus.class, metadata);
    }

}

