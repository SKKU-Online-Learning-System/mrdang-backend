package cs.skku.edu.mrdang.domain.internship.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInternship is a Querydsl query type for Internship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInternship extends EntityPathBase<Internship> {

    private static final long serialVersionUID = 211503286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInternship internship = new QInternship("internship");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    public final cs.skku.edu.mrdang.domain.user.entity.QUser author;

    public final QCompany company;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory> jobs = this.<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory>createSet("jobs", cs.skku.edu.mrdang.domain.category.entity.Category.class, cs.skku.edu.mrdang.domain.category.entity.QCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final QProject project;

    public final SetPath<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory> techStacks = this.<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory>createSet("techStacks", cs.skku.edu.mrdang.domain.category.entity.Category.class, cs.skku.edu.mrdang.domain.category.entity.QCategory.class, PathInits.DIRECT2);

    public final cs.skku.edu.mrdang.domain.file.entity.QImage thumbnail;

    public final StringPath title = createString("title");

    public final StringPath videoUrl = createString("videoUrl");

    public final NumberPath<Long> viewCount = createNumber("viewCount", Long.class);

    public final QWork work;

    public QInternship(String variable) {
        this(Internship.class, forVariable(variable), INITS);
    }

    public QInternship(Path<? extends Internship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInternship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInternship(PathMetadata metadata, PathInits inits) {
        this(Internship.class, metadata, inits);
    }

    public QInternship(Class<? extends Internship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new cs.skku.edu.mrdang.domain.user.entity.QUser(forProperty("author")) : null;
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
        this.thumbnail = inits.isInitialized("thumbnail") ? new cs.skku.edu.mrdang.domain.file.entity.QImage(forProperty("thumbnail")) : null;
        this.work = inits.isInitialized("work") ? new QWork(forProperty("work")) : null;
    }

}

