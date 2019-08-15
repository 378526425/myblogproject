package com.wl.common.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QBaseObject is a Querydsl query type for BaseObject
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBaseObject extends EntityPathBase<BaseObject> {

    private static final long serialVersionUID = -1609842364L;

    public static final QBaseObject baseObject = new QBaseObject("baseObject");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    public final StringPath creator = createString("creator");

    public final StringPath id = createString("id");

    public final DateTimePath<java.util.Date> modifiedTime = createDateTime("modifiedTime", java.util.Date.class);

    public final StringPath modifier = createString("modifier");

    public QBaseObject(String variable) {
        super(BaseObject.class, forVariable(variable));
    }

    public QBaseObject(Path<? extends BaseObject> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseObject(PathMetadata metadata) {
        super(BaseObject.class, metadata);
    }

}

