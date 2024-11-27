package cs.skku.edu.mrdang.domain.enrollment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideoEnrollment is a Querydsl query type for VideoEnrollment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoEnrollment extends EntityPathBase<VideoEnrollment> {

    private static final long serialVersionUID = -1448063231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVideoEnrollment videoEnrollment = new QVideoEnrollment("videoEnrollment");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isWatched = createBoolean("isWatched");

    public final QLectureEnrollment lectureEnrollment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath note = createString("note");

    public final cs.skku.edu.mrdang.domain.user.entity.QUser user;

    public final cs.skku.edu.mrdang.domain.lecture.entity.QVideo video;

    public QVideoEnrollment(String variable) {
        this(VideoEnrollment.class, forVariable(variable), INITS);
    }

    public QVideoEnrollment(Path<? extends VideoEnrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVideoEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVideoEnrollment(PathMetadata metadata, PathInits inits) {
        this(VideoEnrollment.class, metadata, inits);
    }

    public QVideoEnrollment(Class<? extends VideoEnrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureEnrollment = inits.isInitialized("lectureEnrollment") ? new QLectureEnrollment(forProperty("lectureEnrollment"), inits.get("lectureEnrollment")) : null;
        this.user = inits.isInitialized("user") ? new cs.skku.edu.mrdang.domain.user.entity.QUser(forProperty("user")) : null;
        this.video = inits.isInitialized("video") ? new cs.skku.edu.mrdang.domain.lecture.entity.QVideo(forProperty("video")) : null;
    }

}

