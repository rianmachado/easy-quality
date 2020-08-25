package br.com.easy.quality.form.adapter.mongo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPerguntaEntity is a Querydsl query type for PerguntaEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPerguntaEntity extends BeanPath<PerguntaEntity> {

    private static final long serialVersionUID = -1672601045L;

    public static final QPerguntaEntity perguntaEntity = new QPerguntaEntity("perguntaEntity");

    public final StringPath descricao = createString("descricao");

    public final StringPath resposta = createString("resposta");

    public QPerguntaEntity(String variable) {
        super(PerguntaEntity.class, forVariable(variable));
    }

    public QPerguntaEntity(Path<? extends PerguntaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerguntaEntity(PathMetadata metadata) {
        super(PerguntaEntity.class, metadata);
    }

}

