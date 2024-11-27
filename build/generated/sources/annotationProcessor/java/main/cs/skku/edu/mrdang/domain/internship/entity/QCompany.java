package cs.skku.edu.mrdang.domain.internship.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCompany extends BeanPath<Company> {

    private static final long serialVersionUID = -798669547L;

    public static final QCompany company = new QCompany("company");

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public final StringPath url = createString("url");

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

