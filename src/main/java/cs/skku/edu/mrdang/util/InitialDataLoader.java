package cs.skku.edu.mrdang.util;

import cs.skku.edu.mrdang.domain.category.dto.CategoryDTO;
import cs.skku.edu.mrdang.domain.category.entity.Category;
import cs.skku.edu.mrdang.domain.category.entity.CategoryType;
import cs.skku.edu.mrdang.domain.category.repository.CategoryRepository;
import cs.skku.edu.mrdang.domain.category.service.CategoryService;
import cs.skku.edu.mrdang.domain.file.entity.Image;
import cs.skku.edu.mrdang.domain.file.repository.ImageRepository;
import cs.skku.edu.mrdang.domain.internship.entity.Company;
import cs.skku.edu.mrdang.domain.internship.entity.Internship;
import cs.skku.edu.mrdang.domain.internship.entity.Project;
import cs.skku.edu.mrdang.domain.internship.entity.Work;
import cs.skku.edu.mrdang.domain.internship.repository.InternshipRepository;
import cs.skku.edu.mrdang.domain.lecture.dto.LectureDTO;
import cs.skku.edu.mrdang.domain.lecture.entity.Lecture;
import cs.skku.edu.mrdang.domain.lecture.entity.Video;
import cs.skku.edu.mrdang.domain.lecture.repository.LectureRepository;
import cs.skku.edu.mrdang.domain.lecture.repository.LectureVideoRepository;
import cs.skku.edu.mrdang.domain.lecture.repository.VideoRepository;
import cs.skku.edu.mrdang.domain.lecture.service.LectureService;
import cs.skku.edu.mrdang.domain.roadmap.dto.RoadmapDTO;
import cs.skku.edu.mrdang.domain.roadmap.entity.Roadmap;
import cs.skku.edu.mrdang.domain.roadmap.repository.RoadmapRepository;
import cs.skku.edu.mrdang.domain.roadmap.service.RoadmapService;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.service.UserService;
import cs.skku.edu.mrdang.security.sso.SSOUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Profile({"local", "prod"})
@Component
public class InitialDataLoader implements ApplicationRunner {
    private final LectureService lectureService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final RoadmapService roadmapService;

    private final ImageRepository imageRepository;
    private final LectureRepository lectureRepository;
    private final VideoRepository videoRepository;
    private final LectureVideoRepository lectureVideoRepository;
    private final CategoryRepository categoryRepository;
    private final InternshipRepository internshipRepository;
    private final RoadmapRepository roadmapRepository;

    @Transactional
    public void run(ApplicationArguments args) {
        defaultThumbnail();

        성성특();
        성대의성대한스토리();
        글로벌IT전문가와킹고인의만남();

        generateCategory();
        generateInternship();;
        generateRoadmap();
    }

    private void defaultThumbnail() {
        Image image = Image.builder()
                .filename("article_no_img.png")
                .path("/attach/6714fba8-5678-4bcd-99b8-e6222b228f63.png")
                .build();

        imageRepository.save(image);
    }

    private void 성성특() {
        String title = "성대의 성대한 특강";
        String tutor = "성균관대학교 소프트웨어융합대학";
        String description = "성균관대 소프트웨어융합대학에서 일반인을 대상으로 제공하는 IT 특강입니다";

        LectureDTO.CreateRequest request = LectureDTO.CreateRequest.builder()
                .title(title)
                .tutor(tutor)
                .description(description)
                .content("컴퓨터 사이언스 전반의 내용을 비전공자도 쉽게 이해할 수 있는 특강입니다.")
                .thumbnailId(1L)
                .build();

        Long lectureId = lectureService.createLecture(request);

        List<Video> newVideos = List.of(
                Video.builder()
                        .title("소개영상 - 이은석 소프트웨어융합대학장")
                        .duration(LocalTime.of(0, 3, 9))
                        .url("B0xxnpe-eQU")
                        .build(),
                Video.builder()
                        .title("어떻게 지식이 있는 인공지능을 만들 것인가 (1) - 박호건 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 13, 12))
                        .url("NB_BSLqc16U")
                        .build(),
                Video.builder()
                        .title("어떻게 지식이 있는 인공지능을 만들 것인가 (2) - 박호건 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 11, 2))
                        .url("u39G8qLk2JE")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 박호건 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 5, 50))
                        .url("X7TyB6Fw5Mo")
                        .build(),
                Video.builder()
                        .title("사물인터넷(IoT) (1) - 정재훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 54))
                        .url("6UhKRtZhz6Q")
                        .build(),
                Video.builder()
                        .title("사물인터넷(IoT) (2) - 정재훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 14, 16))
                        .url("-W-tuciQd1g")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 정재훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 5, 50))
                        .url("WGaigq9U1Dg")
                        .build(),
                Video.builder()
                        .title("네트워크는 어디에나 있다 (1) - 김영훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 7, 35))
                        .url("OdMnr5gBDII")
                        .build(),
                Video.builder()
                        .title("네트워크는 어디에나 있다 (2) - 김영훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 9, 14))
                        .url("7IFXEoi7kSs")
                        .build(),
                Video.builder()
                        .title("데이터로 보는 인간행동 (1) - 김장현 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 12, 54))
                        .url("IIS_xmkNzAw")
                        .build(),
                Video.builder()
                        .title("데이터로 보는 인간행동 (2) - 김장현 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 10, 04))
                        .url("gZ61PYmntsE")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 김장현 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 3, 44))
                        .url("p5-DCQ7aO_E")
                        .build(),
                Video.builder()
                        .title("머신러닝을 위한 컴퓨터 시스템 (1) - 조형민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 11, 41))
                        .url("AiK8-L-m3p0")
                        .build(),
                Video.builder()
                        .title("머신러닝을 위한 컴퓨터 시스템 (2) - 조형민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 12, 22))
                        .url("K8d36UWP44s")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 조형민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 3, 41))
                        .url("ZR8yVnZ5-gs")
                        .build(),
                Video.builder()
                        .title("시각화(Visualization) (1) - 조재민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 13, 15))
                        .url("pB5ZrET2juo")
                        .build(),
                Video.builder()
                        .title("시각화(Visualization) (2) - 조재민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 11, 56))
                        .url("WlaQcmjhV5Y")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 조재민 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 5, 7))
                        .url("xIQ70V2kYjQ")
                        .build(),
                Video.builder()
                        .title("자연어 처리 (1) - 박진영 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 8, 46))
                        .url("YBfI1xQCGDU")
                        .build(),
                Video.builder()
                        .title("자연어 처리 (2) - 박진영 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 9, 17))
                        .url("UD1PcbGL8aY")
                        .build(),
                Video.builder()
                        .title("강연 이후 Q&A - 박진영 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 6, 38))
                        .url("i9SS6z3sOnE")
                        .build(),
                Video.builder()
                        .title("컴퓨터 비전 미리보기 (1) - 허재필 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 10, 58))
                        .url("60LBRZJtSp0")
                        .build(),
                Video.builder()
                        .title("컴퓨터 비전 미리보기 (2) - 허재필 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 9, 10))
                        .url("8cj0WmSUnig")
                        .build(),
                Video.builder()
                        .title("실시간 시스템과 머신 러닝 (1) - 이진규 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 8, 38))
                        .url("_UElDTDnSSk")
                        .build(),
                Video.builder()
                        .title("실시간 시스템과 머신 러닝 (2) - 이진규 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 12, 58))
                        .url("SdVnu3XS4zE")
                        .build(),
                Video.builder()
                        .title("자기 주도 학습을 실천하는 AI - 김유성 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 19))
                        .url("-lEtHLQ8U58")
                        .build(),
                Video.builder()
                        .title("행성 궤도와 머신러닝 - 김재광 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 13, 18))
                        .url("I0P39Ub0OOo")
                        .build(),
                Video.builder()
                        .title("Research Trends in System Security - 이호준 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 14, 26))
                        .url("ngEqq1706aQ")
                        .build(),
                Video.builder()
                        .title("Journey to a Competitive Software Engineer (1) - 이은석 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 10, 45))
                        .url("2o3YqMjS_yY")
                        .build(),
                Video.builder()
                        .title("Journey to a Competitive Software Engineer (2) - 이은석 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 9, 21))
                        .url("vIAwmheFj60")
                        .build(),
                Video.builder()
                        .title("정신건강과 인공지능 - 한진영 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 16, 21))
                        .url("jfuKfdx2u8k")
                        .build(),
                Video.builder()
                        .title("인간-AI 인터랙션과 헬스 케어 - 송하연 성균관대 인터랙션사이언스학과 교수")
                        .duration(LocalTime.of(0, 16, 24))
                        .url("bYDN4ydcx4U")
                        .build(),
                Video.builder()
                        .title("AI와 스토리텔링 - 정윤경 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 4))
                        .url("kgoHGJDNpNs")
                        .build(),
                Video.builder()
                        .title("연결망 분석을 연구에 응용한 사례들 - 김장현 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 19, 42))
                        .url("mQOXrV9iqgM")
                        .build(),
                Video.builder()
                        .title("Chatbot? Meaning, Working, Types, and Examples (1) - 오하영 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 19, 47))
                        .url("JBTq1I3q0tE")
                        .build(),
                Video.builder()
                        .title("Chatbot? Meaning, Working, Types, and Examples (2) - 오하영 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 20, 20))
                        .url("sWAZhDDUa-c")
                        .build(),
                Video.builder()
                        .title("화이트-박스 소프트웨어 테스팅 - 차수영 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 16, 39))
                        .url("3u_TYZwPgwo")
                        .build(),
                Video.builder()
                        .title("Programming Languages - 황성재 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 18, 30))
                        .url("ELfNFKTMFWY")
                        .build(),
                Video.builder()
                        .title("AI/Data Science 최신 Trend (1) - 우사이먼성일 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 19, 55))
                        .url("qWHyFFpTD_k")
                        .build(),
                Video.builder()
                        .title("AI/Data Science 최신 Trend (2) - 우사이먼성일 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 17, 5))
                        .url("IPlrg7luH9k")
                        .build(),
                Video.builder()
                        .title("Trustworthy AI - 타메르 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 38))
                        .url("BcE6vR0_Akk")
                        .build(),
                Video.builder()
                        .title("콘텐츠 분야 인공지능 적용 사례 - 장병희 성균관대 컬처앤테크놀로지융합전공 교수")
                        .duration(LocalTime.of(0, 14, 42))
                        .url("j3sJQyax8E4")
                        .build(),
                Video.builder()
                        .title("AI와 클라우드, 기후온난화에 미치는 영향 - 서의성 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 22, 38))
                        .url("CA-CTE88DYM")
                        .build(),
                Video.builder()
                        .title("클라우드 기반 보안 서비스 시스템 - 정재훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 25, 41))
                        .url("ECPVkuYh0ao")
                        .build(),
                Video.builder()
                        .title("ChatGPT란 무엇인가 (1) - 고영중 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 29, 1))
                        .url("5ndbqo4ngHs")
                        .build(),
                Video.builder()
                        .title("ChatGPT란 무엇인가 (2) - 고영중 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 30, 3))
                        .url("O5jkWaXYI1Y")
                        .build(),
                Video.builder()
                        .title("가상현실 기술의 발전 - 류은석 성균관대 실감미디어공학과 교수")
                        .duration(LocalTime.of(0, 20, 58))
                        .url("WrvJleYYIec")
                        .build(),
                Video.builder()
                        .title("ChatGPT meets Robots - 우홍욱 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 18, 40))
                        .url("Ua45hYWZGok")
                        .build(),
                Video.builder()
                        .title("네트워크 어렵지 않아요 - 김영훈 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 58))
                        .url("31odq7bv0Zg")
                        .build(),
                Video.builder()
                        .title("사람에서 장소로: 우리 모두가 공유하는 회복탄성력 레시피 - 이창준 성균관대 컬쳐앤테크놀로지 교수")
                        .duration(LocalTime.of(0, 12, 30))
                        .url("ElBFEW-FioM")
                        .build(),
                Video.builder()
                        .title("융합과 인공지능에 대한 경제학적 단상 - 이대호 성균관대 인공지능융합전공 교수")
                        .duration(LocalTime.of(0, 10, 42))
                        .url("UU9rsWxigIA")
                        .build(),
                Video.builder()
                        .title("설명가능한 인공지능에 대하여 - 박호건 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 21, 18))
                        .url("wTyq5oaqc0E")
                        .build(),
                Video.builder()
                        .title("인공지능을 활용한 사이버 보안 - 구형준 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 15, 19))
                        .url("FL87hcrwAzE")
                        .build(),
                Video.builder()
                        .title("소원을 말해봐! 검색과 추천 - 이종욱 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 25, 37))
                        .url("YMUwjHChXwc")
                        .build(),
                Video.builder()
                        .title("메모리와 저장곤간의 기술 최신동향 - 엄영익 성균관대 소프트웨어학과 교수")
                        .duration(LocalTime.of(0, 19, 06))
                        .url("K72BfQwi1bY")
                        .build(),
                Video.builder()
                        .title("소통과 테크놀로지의 역사 - 김장현 성균관대 글로벌융합학부 교수")
                        .duration(LocalTime.of(0, 34, 8))
                        .url("MTLGuWlhJOY")
                        .build(),
                Video.builder()
                        .title("음성생성 그 한계를 찾아서 - 박은일 성균관대 인공지능융합학과 교수")
                        .duration(LocalTime.of(0, 12, 58))
                        .url("OL3tOu4C95U")
                        .build()
        );
        videoRepository.saveAll(newVideos);

        List<LectureDTO.AddVideoRequest> requests = new ArrayList<>();
        for(int i=0; i<newVideos.size(); i++) {
            requests.add(LectureDTO.AddVideoRequest.builder()
                    .videoId(newVideos.get(i).getId())
                    .index((long)i+1)
                    .build()
            );
        }
        lectureService.addVideos(lectureId, requests);
    }

    void 성대의성대한스토리() {
        LectureDTO.CreateRequest createRequest = LectureDTO.CreateRequest.builder()
                .title("성대의 성대한 스토리")
                .description("성균관대학교 소프트웨어 융합대학에서 제공하는 강의입니다.")
                .content("다양한 연구 주제를 다룹니다.")
                .tutor("성균관대 소프트웨어융합대학")
                .thumbnailId(1L)
                .build();
        Long lectureId = lectureService.createLecture(createRequest);

        List<Video> videos = List.of(
                Video.builder()
                        .title("그래프 구조와 Transformer를 활용한 질의 기반 문서 요약")
                        .url("6z6eyfiCNUA")
                        .duration(LocalTime.of(0, 24, 53))
                        .build(),
                Video.builder()
                        .title("Optimizing Key-Value Store for Modern Heterogeneous Storage Devices")
                        .url("FPiGWiUffFA")
                        .duration(LocalTime.of(0, 22, 57))
                        .build(),
                Video.builder()
                        .title("SWAM: Revisiting Swap and OOMK for Improving App Responsiveness on Mobile Devices")
                        .url("cpbMtFX22JQ")
                        .duration(LocalTime.of(0, 23, 34))
                        .build(),
                Video.builder()
                        .title("저자의 의도를 반영하여 데이터 시각화에 대한 설명을 만드는 시스템, Intentable")
                        .url("jSuUYv-ZOYU")
                        .duration(LocalTime.of(0, 20, 16))
                        .build(),
                Video.builder()
                        .title("Model Adaptation for Time Constrained Embodied Control_Minjong Yoo")
                        .url("34c1cBRAj98")
                        .duration(LocalTime.of(0, 19, 45))
                        .build(),
                Video.builder()
                        .title("Locks as a Resource: Fairly Scheduling Lock Occupation with CFL_Jonggyu Park")
                        .url("EyeXP2gt4gs")
                        .duration(LocalTime.of(0, 20, 55))
                        .build(),
                Video.builder()
                        .title("Risk-Conditioned Reinforcement Learning_Gwangpyo Yoo")
                        .url("ORisCOp0MwE")
                        .duration(LocalTime.of(0, 24, 00))
                        .build(),
                Video.builder()
                        .title("Quality-Agnostic Deepfake Detection with Intra-model Collaborative Learning_Binh M. Le")
                        .url("s9Y6mM5m9B4")
                        .duration(LocalTime.of(0, 17, 00))
                        .build(),
                Video.builder()
                        .title("Revisiting Swap and OOMK for Improving Application Responsiveness_Geunsik Lim")
                        .url("vvSvSUaX44k")
                        .duration(LocalTime.of(0, 24, 59))
                        .build(),
                Video.builder()
                        .title("Efficient Policy Adaptation with Contrastive prompt Ensemble_WonJe Choi")
                        .url("Azo3TnqVNFE")
                        .duration(LocalTime.of(0, 26, 33))
                        .build(),
                Video.builder()
                        .title("Robust Policy Learning via Offline Skill Diffustion_Woo Kyung Kim")
                        .url("l_bKJOOwkAc")
                        .duration(LocalTime.of(0, 23, 55))
                        .build(),
                Video.builder()
                        .title("One-shot Imitation in a Non-Stationary Environment via Multi-Modal Skill_SangWoo Shin")
                        .url("uhQ-FHnG3K0")
                        .duration(LocalTime.of(0, 19, 39))
                        .build()
        );
        videoRepository.saveAll(videos);

        List<LectureDTO.AddVideoRequest> requests = new ArrayList<>();
        for(int i=0; i<videos.size(); i++) {
            LectureDTO.AddVideoRequest request = LectureDTO.AddVideoRequest.builder()
                    .index((long)i+1)
                    .videoId(videos.get(i).getId())
                    .build();
            requests.add(request);
        }

        lectureService.addVideos(lectureId, requests);
    }

    void 글로벌IT전문가와킹고인의만남() {
        LectureDTO.CreateRequest  createRequest = LectureDTO.CreateRequest.builder()
                .title("글로벌IT전문가와 킹고인의 만남")
                .tutor("성균관대 소프트웨어융합대학")
                .description("성균관대학교 소프트웨어 융합대학에서 제공하는 강의입니다.")
                .content("글로벌 IT전문가가 킹고인에게 제공하는 세미나 영상입니다.")
                .thumbnailId(1L)
                .build();

        Long lectureId = lectureService.createLecture(createRequest);

        List<Video> videos = List.of(
                Video.builder()
                        .title("Cloud - Past, Present & Future")
                        .url("7yrJZaDLQhk")
                        .duration(LocalTime.of(1, 20, 16))
                        .build(),
                Video.builder()
                        .title("Prompt Engineering - How to design conversations with AI")
                        .url("lDC5nH1UDO0")
                        .duration(LocalTime.of(0, 58, 26))
                        .build(),
                Video.builder()
                        .title("마케팅 데이터 분석가 업무와 데이터 분석 / 시각화 기술")
                        .url("41GUMcoyQXE")
                        .duration(LocalTime.of(0, 58, 16))
                        .build(),
                Video.builder()
                        .title("Knowledge is power, but Creativity is a Superpower!")
                        .url("JwnUmPkN2I8")
                        .duration(LocalTime.of(1, 4, 24))
                        .build()
        );
        videoRepository.saveAll(videos);

        List<LectureDTO.AddVideoRequest> requests = new ArrayList<>();
        for(int i=0; i<videos.size(); i++) {
            LectureDTO.AddVideoRequest request = LectureDTO.AddVideoRequest.builder()
                    .index((long)i+1)
                    .videoId(videos.get(i).getId())
                    .build();
            requests.add(request);
        }

        lectureService.addVideos(lectureId, requests);
    }

    private void generateCategory() {
        List<CategoryDTO.CreateRequest> requests = List.of(
                CategoryDTO.CreateRequest.builder()
                        .name("평론가의 길")
                        .type(CategoryType.로드맵)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("컴공의 신이 되는 방법")
                        .type(CategoryType.로드맵)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("CS")
                        .type(CategoryType.기술스택)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("Spring")
                        .type(CategoryType.기술스택)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("React.js")
                        .type(CategoryType.기술스택)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("svelte.js")
                        .type(CategoryType.기술스택)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("k8s")
                        .type(CategoryType.기술스택)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("백엔드 개발자")
                        .type(CategoryType.직무)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("SRE")
                        .type(CategoryType.직무)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("플랫폼 엔지니어")
                        .type(CategoryType.직무)
                        .build(),
                CategoryDTO.CreateRequest.builder()
                        .name("연구원")
                        .type(CategoryType.직무)
                        .build()
        );

        requests.stream().forEach(request -> categoryService.createCategory(request));
    }

    void generateInternship() {
        final SSOUser ssoUser = SSOUser.builder()
                .userId("molla")
                .userName("정주희")
                .studentId("2020310000")
                .department("소프트웨어학과")
                .degree("학사")
                .status("재학")
                .group("N/A")
                .employeeId("N/A")
                .build();

        final User author = userService.getOrCreateUser(ssoUser);

        final Company COMPANY_RINGLE = Company.builder()
                .name("Ringle")
                .description("영미권 명문대 튜터와 일대일 대화를 나누며 영어를 교정받을 수 있는 화상 영어 서비스")
                .url("https://www.ringleplus.com")
                .build();

        final Project PROJECT_RINGLE = Project.builder()
                .name("백엔드...")
                .description("온보딩, BuildKite, 3.0챌린지 백엔드 작업")
                .build();

        final Work WORK_RINGLE = Work.builder()
                .place("해외")
                .startDate(LocalDate.of(2023, 7, 1))
                .endDate(LocalDate.of(2023, 12, 31))
                .build();

        final Category tech = categoryRepository.findCategoryByName("Spring");
        final Category job = categoryRepository.findCategoryByName("백엔드 개발자");

        final Image image = imageRepository.findById(1L).orElseGet(() -> null);

        final Internship INTERNSHIP_RINGLE = Internship.builder()
                .id(1L)
                .title("화상영어서비스 개발자가 전해주는 백엔드 인턴 이야기")
                .description("성균관대학교 해외 ICT 인턴십 후기입니다.")
                .content("인턴 준비부터 인턴 전후의 모습까지 모든 걸 담았습니다.")
                .videoUrl("PePdoGbT5gM")
                .company(COMPANY_RINGLE)
                .project(PROJECT_RINGLE)
                .work(WORK_RINGLE)
                .viewCount(0L)
                .author(author)
                .techStacks(Set.of(tech))
                .jobs(Set.of(job))
                .thumbnail(image)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        internshipRepository.save(INTERNSHIP_RINGLE);
    }

    void generateRoadmap() {
        List<Category> categories = categoryRepository.findAll();
        Category found = categories.stream()
                .filter(category -> category.getType().equals(CategoryType.로드맵))
                .filter(category -> category.getName().contains("컴공의"))
                .findFirst()
                .orElseGet(null);

        RoadmapDTO.CreateRequest request = RoadmapDTO.CreateRequest.builder()
                .trackId(found.getId())
                .description("컴공의 신이 되는 방법")
                .content("소프트웨어 융합대학에서 알려주는 컴공 로드맵")
                .thumbnailId(null)
                .build();

        Long roadmapId = roadmapService.createRoadmap(request);

        Lecture lecture = lectureRepository.findAll().stream()
                .findFirst()
                .orElse(null);
        Roadmap roadmap = roadmapRepository.findById(roadmapId)
                .orElse(null);
        roadmap.getLectures().add(lecture);

        roadmapRepository.save(roadmap);
    }
}