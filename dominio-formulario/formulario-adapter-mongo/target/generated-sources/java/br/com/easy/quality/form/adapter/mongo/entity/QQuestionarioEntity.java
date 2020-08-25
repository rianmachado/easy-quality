package br.com.easy.quality.form.adapter.mongo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionarioEntity is a Querydsl query type for QuestionarioEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionarioEntity extends EntityPathBase<QuestionarioEntity> {

    private static final long serialVersionUID = -1781687880L;

    public static final QQuestionarioEntity questionarioEntity = new QQuestionarioEntity("questionarioEntity");

    public final DateTimePath<java.time.LocalDateTime> dataCriacao = createDateTime("dataCriacao", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dataEdicao = createDateTime("dataEdicao", java.time.LocalDateTime.class);

    public final StringPath id = createString("id");

    public final ListPath<PerguntaEntity, QPerguntaEntity> perguntas = this.<PerguntaEntity, QPerguntaEntity>createList("perguntas", PerguntaEntity.class, QPerguntaEntity.class, PathInits.DIRECT2);

    public final BooleanPath status = createBoolean("status");

    public final StringPath titulo = createString("titulo");

    public QQuestionarioEntity(String variable) {
        super(QuestionarioEntity.class, forVariable(variable));
    }

    public QQuestionarioEntity(Path<? extends QuestionarioEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionarioEntity(PathMetadata metadata) {
        super(QuestionarioEntity.class, metadata);
    }

}

