package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.media.MediaRequest;
import ma.musfatihii.QuizTime.dto.question.QuestionReq;
import ma.musfatihii.QuizTime.dto.question.QuestionResp;
import ma.musfatihii.QuizTime.exception.*;
import ma.musfatihii.QuizTime.model.Media;
import ma.musfatihii.QuizTime.model.Question;
import ma.musfatihii.QuizTime.enums.QuestionType;
import ma.musfatihii.QuizTime.repository.LevelRepository;
import ma.musfatihii.QuizTime.repository.MediaRepository;
import ma.musfatihii.QuizTime.repository.QuestionRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements ServiceInterface<QuestionReq,Long,QuestionResp> {

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;

    private final LevelRepository levelRepository;

    private final MediaRepository mediaRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           ModelMapper modelMapper,
                           LevelRepository levelRepository,
                           MediaRepository mediaRepository)
    {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.mediaRepository = mediaRepository;
        this.levelRepository = levelRepository;
    }


    @Override
    public Optional<QuestionResp> save(QuestionReq questionReq) {

        if(!isLevelValid(questionReq) || !isNbrResponsesValid(questionReq) || !isRespsCorrectRespsValid(questionReq)
                || !isQuestionTypeNbrCorrectResponsesValid(questionReq) || !isMaxScoreValid(questionReq))
        {
            throw new InfosNotCorrectException("Infos Question incorrectes");
        }

        try {
            List<MediaRequest> mediaList = questionReq.getMediaList();

            questionReq.setMediaList(new ArrayList<>());

            Question savedQuestion = questionRepository.save(modelMapper.map(questionReq,Question.class));

            List<Media> toSaveMedias = new ArrayList<>();

            for (MediaRequest media : mediaList) {
                Media toSaveMedia = modelMapper.map(media, Media.class);
                toSaveMedia.setQuestion(savedQuestion);
                toSaveMedias.add(toSaveMedia);
            }

            List<Media> savedMedias = mediaRepository.saveAll(toSaveMedias);

            savedQuestion.setMediaList(savedMedias);

            return Optional.of(
                    modelMapper.map(
                            savedQuestion
                            ,QuestionResp.class)
            );
        } catch (Exception e) {throw new ServerErrorException("Erreur serveur");}
    }

    @Override
    public Optional<QuestionResp> update(QuestionReq questionReq) {
        return Optional.empty();
    }

    @Override
    public List<QuestionResp> findAll() {
        return List.of(modelMapper.map(questionRepository.findAll(),QuestionResp[].class));
    }

    @Override
    public Optional<QuestionResp> findById(Long id) {
        Question foundQuestion = questionRepository.findById(id)
                                .orElseThrow(()->new NotFoundException("Question introuvable"));
        try{
            return Optional.of(
                    modelMapper.map(foundQuestion,QuestionResp.class)
            );
        } catch(Exception e){throw new ServerErrorException("Erreur Serveur");}
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private boolean isNbrResponsesValid(QuestionReq questionReq)
    {return questionReq.getNbrResponses()>=2;}

    private boolean isRespsCorrectRespsValid(QuestionReq questionReq)
    {return questionReq.getNbrResponses()>=questionReq.getNbrCorrectResponses();}

    private boolean isQuestionTypeNbrCorrectResponsesValid(QuestionReq questionReq)
    {
        return (questionReq.getNbrCorrectResponses()>1 && questionReq.getType().name().equals(QuestionType.ManyTrueResponses.name())
                || questionReq.getNbrCorrectResponses()==1 && questionReq.getType().name().equals(QuestionType.OneTrueResponse.name()));

    }

    private boolean isMaxScoreValid(QuestionReq questionReq) {
        return (questionReq.getMaxScore() >= levelRepository.findById(questionReq.getLevel().getId()).get().getMinScore() &&
                questionReq.getMaxScore() <= levelRepository.findById(questionReq.getLevel().getId()).get().getMaxScore());
    }

    private boolean isLevelValid(QuestionReq questionReq){
        return levelRepository.existsById(questionReq.getLevel().getId());
    }
}
