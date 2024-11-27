package cs.skku.edu.mrdang.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1144088522L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review1 = new QReview("review1");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final cs.skku.edu.mrdang.domain.lecture.entity.QLecture lecture;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath review = createString("review");

    public final cs.skku.edu.mrdang.domain.roadmap.entity.QRoadmap roadmap;

    public final cs.skku.edu.mrdang.domain.user.entity.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new cs.skku.edu.mrdang.domain.lecture.entity.QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.roadmap = inits.isInitialized("roadmap") ? new cs.skku.edu.mrdang.domain.roadmap.entity.QRoadmap(forProperty("roadmap"), inits.get("roadmap")) : null;
        this.user = inits.isInitialized("user") ? new cs.skku.edu.mrdang.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

