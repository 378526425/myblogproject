package com.wl.blog.server.entity.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.wl.blog.server.entity.CommentMessage;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QCommentMessage is a Querydsl query type for CommentMessage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommentMessage extends EntityPathBase<CommentMessage> {

    private static final long serialVersionUID = 1693040648L;

    public static final QCommentMessage commentMessage = new QCommentMessage("commentMessage");

    public final com.wl.common.entity.QBaseObject _super = new com.wl.common.entity.QBaseObject(this);

    public final StringPath commentId = createString("commentId");

    public final StringPath content = createString("content");

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

    public final StringPath toUserId = createString("toUserId");

    public final StringPath userId = createString("userId");

    public QCommentMessage(String variable) {
        super(CommentMessage.class, forVariable(variable));
    }

    public QCommentMessage(Path<? extends CommentMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentMessage(PathMetadata metadata) {
        super(CommentMessage.class, metadata);
    }

}

