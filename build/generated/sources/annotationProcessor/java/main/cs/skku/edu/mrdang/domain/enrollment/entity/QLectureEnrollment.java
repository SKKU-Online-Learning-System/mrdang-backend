package cs.skku.edu.mrdang.domain.enrollment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureEnrollment is a Querydsl query type for LectureEnrollment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureEnrollment extends EntityPathBase<LectureEnrollment> {

    private static final long serialVersionUID = 1892507588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureEnrollment lectureEnrollment = new QLectureEnrollment("lectureEnrollment");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    public final NumberPath<Long> completedVideoCount = createNumber("completedVideoCount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCompleted = createBoolean("isCompleted");

    public final BooleanPath isLiked = createBoolean("isLiked");

    public final BooleanPath isRegistered = createBoolean("isRegistered");

    public final cs.skku.edu.mrdang.domain.lecture.entity.QLecture lecture;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Double> progressRate = createNumber("progressRate", Double.class);

    public final cs.skku.edu.mrdang.domain.user.entity.QUser user;

    public final NumberPath<Long> videoCount = createNumber("videoCount", Long.class);

    public final ListPath<VideoEnrollment, QVideoEnrollment> videoEnrollments = this.<VideoEnrollment, QVideoEnrollment>createList("videoEnrollments", VideoEnrollment.class, QVideoEnrollment.class, PathInits.DIRECT2);

    public QLectureEnrollment(String variable) {
        this(LectureEnrollment.class, forVariable(variable), INITS);
    }

    public QLectureEnrollment(Path<? extends LectureEnrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureEnrollment(PathMetadata metadata, PathInits inits) {
        this(LectureEnrollment.class, metadata, inits);
    }

    public QLectureEnrollment(Class<? extends LectureEnrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new cs.skku.edu.mrdang.domain.lecture.entity.QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.user = inits.isInitialized("user") ? new cs.skku.edu.mrdang.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

