package com.wl.blog.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = 292628886L;

    public static final QArticle article = new QArticle("article");

    public final com.wl.common.entity.QBaseObject _super = new com.wl.common.entity.QBaseObject(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.util.Date> createdTime = _super.createdTime;

    //inherited
    public final StringPath creator = _super.creator;

    public final NumberPath<Long> dislikeCount = createNumber("dislikeCount", Long.class);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath img = createString("img");

    public final StringPath introduction = createString("introduction");

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> modifiedTime = _super.modifiedTime;

    //inherited
    public final StringPath modifier = _super.modifier;

    public final NumberPath<Long> readCount = createNumber("readCount", Long.class);

    public final StringPath title = createString("title");

    public final StringPath userId = createString("userId");

    public QArticle(String variable) {
        super(Article.class, forVariable(variable));
    }

    public QArticle(Path<? extends Article> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticle(PathMetadata metadata) {
        super(Article.class, metadata);
    }

}

