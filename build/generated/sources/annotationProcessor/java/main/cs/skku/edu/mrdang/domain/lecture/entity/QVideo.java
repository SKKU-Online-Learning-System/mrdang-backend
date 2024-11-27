package cs.skku.edu.mrdang.domain.lecture.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVideo is a Querydsl query type for Video
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideo extends EntityPathBase<Video> {

    private static final long serialVersionUID = -292185859L;

    public static final QVideo video = new QVideo("video");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final TimePath<java.time.LocalTime> duration = createTime("duration", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<LectureVideo, QLectureVideo> lectureVideos = this.<LectureVideo, QLectureVideo>createList("lectureVideos", LectureVideo.class, QLectureVideo.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public QVideo(String variable) {
        super(Video.class, forVariable(variable));
    }

    public QVideo(Path<? extends Video> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideo(PathMetadata metadata) {
        super(Video.class, metadata);
    }

}

