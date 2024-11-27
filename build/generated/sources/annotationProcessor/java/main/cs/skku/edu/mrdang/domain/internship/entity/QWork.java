package cs.skku.edu.mrdang.domain.internship.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWork is a Querydsl query type for Work
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QWork extends BeanPath<Work> {

    private static final long serialVersionUID = -2031073927L;

    public static final QWork work = new QWork("work");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath place = createString("place");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QWork(String variable) {
        super(Work.class, forVariable(variable));
    }

    public QWork(Path<? extends Work> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWork(PathMetadata metadata) {
        super(Work.class, metadata);
    }

}

