package ma.musfatihii.QuizTime.service.Implementation;


import ma.musfatihii.QuizTime.exception.QuestionNotFoundException;
import ma.musfatihii.QuizTime.exception.QuestionResponsesInfosNotCorrectException;
import ma.musfatihii.QuizTime.model.QuestionResponse;
import ma.musfatihii.QuizTime.repository.QuestionResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionResponsesService {
    private QuestionResponseRepository questionResponseRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    public QuestionResponsesService(QuestionResponseRepository questionResponseRepository)
    {
        this.questionResponseRepository = questionResponseRepository;
    }


    public void addNewQuestionResponses(List<QuestionResponse> questionResponses) {

        if(questionService.findById(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId()).isPresent())
        {
            if(!isSumScoresValid(questionResponses) || !isNbrResponsesValid(questionResponses) || !isAllResponsesValid(questionResponses)){throw new QuestionResponsesInfosNotCorrectException();}
        }
        else
        {
            throw new QuestionNotFoundException(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId());
        }
        try{questionResponseRepository.saveAll(questionResponses);}
        catch (Exception e) {}
    }


    private boolean isSumScoresValid(List<QuestionResponse> questionResponses){
        double sum=0;
        for (QuestionResponse questionResponse : questionResponses) {
            sum+=questionResponse.getScore();
        }
        if(sum==questionService.findById(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId()).get().getMaxScore()){return true;}
        return false;
    }

    private boolean isNbrResponsesValid(List<QuestionResponse> questionResponses)
    {
        if(questionResponses.size()==questionService.findById(questionResponses.get(0).getQuestionResponseCompositeKey().getQuestion().getId()).get().getNbrResponses()){return true;}
        return false;
    }

    private boolean isAllResponsesValid(List<QuestionResponse> questionResponses){
        for (QuestionResponse questionResponse : questionResponses) {
            if(!responseService.findById(questionResponse.getQuestionResponseCompositeKey().getResponse().getId()).isPresent())
            {
                return false;
            }
        }
        return true;
    }
}
