package ma.musfatihii.QuizTime.service;

import ma.musfatihii.QuizTime.model.Quiz;
import ma.musfatihii.QuizTime.repository.QuizRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {
    private QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    public void addNewQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuiz(Long id)
    {
        return quizRepository.findById(id);
    }

}
