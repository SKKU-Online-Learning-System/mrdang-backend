package cs.skku.edu.mrdang.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1321968656L;

    public static final QUser user = new QUser("user");

    public final cs.skku.edu.mrdang.util.QBaseTimeEntity _super = new cs.skku.edu.mrdang.util.QBaseTimeEntity(this);

    public final StringPath academicDegree = createString("academicDegree");

    public final StringPath academicDepartment = createString("academicDepartment");

    public final StringPath academicStatus = createString("academicStatus");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath employeeGroup = createString("employeeGroup");

    public final StringPath employeeId = createString("employeeId");

    public final StringPath glsId = createString("glsId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<cs.skku.edu.mrdang.domain.enrollment.entity.LectureEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QLectureEnrollment> lectureEnrollments = this.<cs.skku.edu.mrdang.domain.enrollment.entity.LectureEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QLectureEnrollment>createList("lectureEnrollments", cs.skku.edu.mrdang.domain.enrollment.entity.LectureEnrollment.class, cs.skku.edu.mrdang.domain.enrollment.entity.QLectureEnrollment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment> roadmapEnrollments = this.<cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment>createList("roadmapEnrollments", cs.skku.edu.mrdang.domain.enrollment.entity.RoadmapEnrollment.class, cs.skku.edu.mrdang.domain.enrollment.entity.QRoadmapEnrollment.class, PathInits.DIRECT2);

    public final EnumPath<UserRole> role = createEnum("role", UserRole.class);

    public final StringPath studentId = createString("studentId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

