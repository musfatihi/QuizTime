package ma.musfatihii.QuizTime.service.Implementation;

import ma.musfatihii.QuizTime.dto.media.MediaResp;
import ma.musfatihii.QuizTime.model.Media;
import ma.musfatihii.QuizTime.repository.MediaRepository;
import ma.musfatihii.QuizTime.service.Interface.ServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements ServiceInterface<Media,Long, MediaResp> {

    private MediaRepository mediaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }
    @Override
    public Optional<MediaResp> save(Media media) {
        return Optional.of(modelMapper.map(mediaRepository.save(media), MediaResp.class));
    }

    public boolean saveAll(List<Media> mediaList) {
        return false;
    }

    @Override
    public Optional<MediaResp> update(Media media) {
        return Optional.empty();
    }

    @Override
    public List<MediaResp> findAll() {
        return null;
    }

    @Override
    public Optional<MediaResp> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
