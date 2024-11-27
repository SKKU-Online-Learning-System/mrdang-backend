package cs.skku.edu.mrdang.domain.enrollment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoadmapEnrollment is a Querydsl query type for RoadmapEnrollment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoadmapEnrollment extends EntityPathBase<RoadmapEnrollment> {

    private static final long serialVersionUID = -1042854142L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoadmapEnrollment roadmapEnrollment = new QRoadmapEnrollment("roadmapEnrollment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCompleted = createBoolean("isCompleted");

    public final BooleanPath isLiked = createBoolean("isLiked");

    public final BooleanPath isRegistered = createBoolean("isRegistered");

    public final NumberPath<Long> progress = createNumber("progress", Long.class);

    public final cs.skku.edu.mrdang.domain.roadmap.entity.QRoadmap roadmap;

    public final cs.skku.edu.mrdang.domain.user.entity.QUser user;

    public QRoadmapEnrollment(String variable) {
        this(RoadmapEnrollment.class, forVariable(variable), INITS);
    }

    public QRoadmapEnrollment(Path<? extends RoadmapEnrollment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoadmapEnrollment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoadmapEnrollment(PathMetadata metadata, PathInits inits) {
        this(RoadmapEnrollment.class, metadata, inits);
    }

    public QRoadmapEnrollment(Class<? extends RoadmapEnrollment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.roadmap = inits.isInitialized("roadmap") ? new cs.skku.edu.mrdang.domain.roadmap.entity.QRoadmap(forProperty("roadmap"), inits.get("roadmap")) : null;
        this.user = inits.isInitialized("user") ? new cs.skku.edu.mrdang.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

