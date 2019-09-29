package com.wl.blog.server.entity.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.wl.blog.server.entity.ArticleComment;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QArticleComment is a Querydsl query type for ArticleComment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleComment extends EntityPathBase<ArticleComment> {

    private static final long serialVersionUID = 661489641L;

    public static final QArticleComment articleComment = new QArticleComment("articleComment");

    public final com.wl.common.entity.QBaseObject _super = new com.wl.common.entity.QBaseObject(this);

    public final StringPath articleId = createString("articleId");

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

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

    public final StringPath userId = createString("userId");

    public QArticleComment(String variable) {
        super(ArticleComment.class, forVariable(variable));
    }

    public QArticleComment(Path<? extends ArticleComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleComment(PathMetadata metadata) {
        super(ArticleComment.class, metadata);
    }

}

