package cs.skku.edu.mrdang.domain.internship.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProject extends BeanPath<Project> {

    private static final long serialVersionUID = -2058464335L;

    public static final QProject project = new QProject("project");

    public final StringPath description = createString("description");

    public final StringPath name = createString("name");

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

