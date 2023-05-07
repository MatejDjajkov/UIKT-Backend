package finki.mk.uiktBackend.service;


import finki.mk.uiktBackend.model.File;
import finki.mk.uiktBackend.model.enums.ExamType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    File getFile(Long id);

    void saveFile(Long id, MultipartFile file, ExamType type);

    List<File> findApprovedFilesForSubject(Long id);
    List<File> findAllPendingFiles();
    List<File> findAllDeclinedFiles();
    void approveFile(Long id);
    void declineFile(Long id);

    void deleteFile(Long id);
}