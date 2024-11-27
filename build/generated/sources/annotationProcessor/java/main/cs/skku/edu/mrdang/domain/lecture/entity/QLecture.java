package cs.skku.edu.mrdang.domain.lecture.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = -2017822336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    public final SetPath<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory> categories = this.<cs.skku.edu.mrdang.domain.category.entity.Category, cs.skku.edu.mrdang.domain.category.entity.QCategory>createSet("categories", cs.skku.edu.mrdang.domain.category.entity.Category.class, cs.skku.edu.mrdang.domain.category.entity.QCategory.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<LectureVideo, QLectureVideo> lectureVideos = this.<LectureVideo, QLectureVideo>createList("lectureVideos", LectureVideo.class, QLectureVideo.class, PathInits.DIRECT2);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> studentCount = createNumber("studentCount", Long.class);

    public final cs.skku.edu.mrdang.domain.file.entity.QImage thumbnail;

    public final StringPath title = createString("title");

    public final TimePath<java.time.LocalTime> totalDuration = createTime("totalDuration", java.time.LocalTime.class);

    public final StringPath tutor = createString("tutor");

    public final NumberPath<Long> videoCount = createNumber("videoCount", Long.class);

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.thumbnail = inits.isInitialized("thumbnail") ? new cs.skku.edu.mrdang.domain.file.entity.QImage(forProperty("thumbnail")) : null;
    }

}

