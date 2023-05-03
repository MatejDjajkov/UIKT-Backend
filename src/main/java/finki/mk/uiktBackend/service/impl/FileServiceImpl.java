package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.File;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.ExamType;
import finki.mk.uiktBackend.model.enums.Status;
import finki.mk.uiktBackend.model.exceptions.FileNotFoundException;
import finki.mk.uiktBackend.repository.FileRepository;
import finki.mk.uiktBackend.service.FileService;
import finki.mk.uiktBackend.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final SubjectService subjectService;

    public FileServiceImpl(FileRepository fileRepository, SubjectService subjectService) {
        this.fileRepository = fileRepository;
        this.subjectService = subjectService;
    }


    @Override
    public File getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public void saveFile(Long id, MultipartFile file, ExamType type) {
        Subject sub = subjectService.getById(id);
        File newFile = new File();
        newFile.setName(file.getOriginalFilename());
        newFile.setSubject(sub);
        newFile.setExamType(type);
        newFile.setMimeType(file.getContentType());
        try {
            newFile.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        newFile.setStatus(Status.PENDING);
        fileRepository.save(newFile);
    }

    @Override
    public List<File> findApprovedFilesForSubject(Long id) {
        Subject subject=subjectService.getById(id);
        return fileRepository.findAllBySubjectAndStatus(subject, Status.APPROVED);
    }

    @Override
    public List<File> findAllPendingFiles() {
        return fileRepository.findAllByStatus(Status.PENDING);
    }

    @Override
    public List<File> findAllDeclinedFiles() {
        return fileRepository.findAllByStatus(Status.DECLINED);
    }

    @Override
    public void approveFile(Long id) {
        File file=fileRepository.getById(id);
        file.setStatus(Status.APPROVED);
        fileRepository.save(file);
    }

    @Override
    public void declineFile(Long id) {
        File file=fileRepository.getById(id);
        file.setStatus(Status.DECLINED);
        fileRepository.save(file);
    }
    @Override
    public void deleteFile(Long id) {
        File file = this.getFile(id);
        fileRepository.delete(file);
    }
}
