package ma.musfatihii.QuizTime.service.Implementation;


import ma.musfatihii.QuizTime.dto.questionResponses.QuestionResponsesReq;
import ma.musfatihii.QuizTime.exception.InfosNotCorrectException;
import ma.musfatihii.QuizTime.exception.ServerErrorException;
import ma.musfatihii.QuizTime.model.QuestionResponse;
import ma.musfatihii.QuizTime.repository.QuestionRepository;
import ma.musfatihii.QuizTime.repository.QuestionResponseRepository;
import ma.musfatihii.QuizTime.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionResponsesService {
    private final QuestionResponseRepository questionResponseRepository;

    private final QuestionRepository questionRepository;

    private final ResponseRepository responseRepository;

    @Autowired
    public QuestionResponsesService(QuestionResponseRepository questionResponseRepository,
                                    QuestionRepository questionRepository,
                                    ResponseRepository responseRepository)
    {
        this.questionResponseRepository = questionResponseRepository;
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }


    private boolean isSameQuestion(List<QuestionResponse> questionResponses){
        for(int i=0;i<questionResponses.size()-1;i++)
        {
            if(questionResponses.get(i).getQuestionResponseCompositeKey().getQuestion().getId()
               !=questionResponses.get(i+1).getQuestionResponseCompositeKey().getQuestion().getId()) return false;
        }
        return true;
    }


    private boolean isSumScoresValid(List<QuestionResponse> questionResponses){
        double sum=0;
        for (QuestionResponse questionResponse : questionResponses) {
            sum+=questionResponse.getScore();
        }
        return (sum==questionRepository.findById(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId()).get().getMaxScore());
    }

    private boolean isNbrResponsesValid(List<QuestionResponse> questionResponses)
    {
        return (questionResponses.size()==questionRepository.findById(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId()).get().getNbrResponses());
    }

    private boolean isAllResponsesValid(List<QuestionResponse> questionResponses){
        for (QuestionResponse questionResponse : questionResponses) {
            if(responseRepository.findById(questionResponse.getQuestionResponseCompositeKey().getResponse().getId()).isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public void save(QuestionResponsesReq questionResponsesReq) {
        List<QuestionResponse> questionResponses = questionResponsesReq.getQuestionResponses();
        if(!isSameQuestion(questionResponses)
        || !isNbrResponsesValid(questionResponses)
        || !isSumScoresValid(questionResponses)
        || !isAllResponsesValid(questionResponses)) throw new InfosNotCorrectException("Infos Question Réponses incorrectes");
        try {
            questionResponseRepository.saveAll(questionResponses);
        }catch (Exception e){throw new ServerErrorException("Erreur serveur");}
    }
}
