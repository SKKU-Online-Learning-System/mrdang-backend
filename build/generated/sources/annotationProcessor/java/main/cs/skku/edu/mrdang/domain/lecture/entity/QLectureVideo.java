package cs.skku.edu.mrdang.domain.lecture.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureVideo is a Querydsl query type for LectureVideo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureVideo extends EntityPathBase<LectureVideo> {

    private static final long serialVersionUID = -1774019877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureVideo lectureVideo = new QLectureVideo("lectureVideo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> index = createNumber("index", Long.class);

    public final QLecture lecture;

    public final QVideo video;

    public QLectureVideo(String variable) {
        this(LectureVideo.class, forVariable(variable), INITS);
    }

    public QLectureVideo(Path<? extends LectureVideo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureVideo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureVideo(PathMetadata metadata, PathInits inits) {
        this(LectureVideo.class, metadata, inits);
    }

    public QLectureVideo(Class<? extends LectureVideo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.video = inits.isInitialized("video") ? new QVideo(forProperty("video")) : null;
    }

}

