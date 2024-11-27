package cs.skku.edu.mrdang.domain.roadmap.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoadmap is a Querydsl query type for Roadmap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoadmap extends EntityPathBase<Roadmap> {

    private static final long serialVersionUID = 44869056L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoadmap roadmap = new QRoadmap("roadmap");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory> jobs = this.<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory>createList("jobs", cs.skku.edu.mrdang.domain.category.entity.Category.class, cs.skku.edu.mrdang.domain.category.entity.QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> lectureCount = createNumber("lectureCount", Long.class);

    public final ListPath<cs.skku.edu.mrdang.domain.lecture.entity.Lecture, cs.skku.edu.mrdang.domain.lecture.entity.QLecture> lectures = this.<cs.skku.edu.mrdang.domain.lecture.entity.Lecture, cs.skku.edu.mrdang.domain.lecture.entity.QLecture>createList("lectures", cs.skku.edu.mrdang.domain.lecture.entity.Lecture.class, cs.skku.edu.mrdang.domain.lecture.entity.QLecture.class, PathInits.DIRECT2);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final ListPath<cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment> roadmapEnrollments = this.<cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment>createList("roadmapEnrollments", cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment.class, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment.class, PathInits.DIRECT2);

    public final NumberPath<Long> studentCount = createNumber("studentCount", Long.class);

    public final cs.skku.edu.mrdang.domain.file.entity.QImage thumbnail;

    public final TimePath<java.time.LocalTime> totalDuration = createTime("totalDuration", java.time.LocalTime.class);

    public final cs.skku.edu.mrdang.domain.category.entity.QCategory track;

    public QRoadmap(String variable) {
        this(Roadmap.class, forVariable(variable), INITS);
    }

    public QRoadmap(Path<? extends Roadmap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoadmap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoadmap(PathMetadata metadata, PathInits inits) {
        this(Roadmap.class, metadata, inits);
    }

    public QRoadmap(Class<? extends Roadmap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.thumbnail = inits.isInitialized("thumbnail") ? new cs.skku.edu.mrdang.domain.file.entity.QImage(forProperty("thumbnail")) : null;
        this.track = inits.isInitialized("track") ? new cs.skku.edu.mrdang.domain.category.entity.QCategory(forProperty("track")) : null;
    }

}

