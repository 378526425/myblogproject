package com.wl.blog.server.entity.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.wl.blog.server.entity.IgnoreResource;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QIgnoreResource is a Querydsl query type for IgnoreResource
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIgnoreResource extends EntityPathBase<IgnoreResource> {

    private static final long serialVersionUID = -533961888L;

    public static final QIgnoreResource ignoreResource = new QIgnoreResource("ignoreResource");

    public final com.wl.common.entity.QBaseObject _super = new com.wl.common.entity.QBaseObject(this);

    public final StringPath aType = createString("aType");

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

    public final StringPath resourceName = createString("resourceName");

    public QIgnoreResource(String variable) {
        super(IgnoreResource.class, forVariable(variable));
    }

    public QIgnoreResource(Path<? extends IgnoreResource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIgnoreResource(PathMetadata metadata) {
        super(IgnoreResource.class, metadata);
    }

}

