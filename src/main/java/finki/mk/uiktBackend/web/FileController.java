package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.File;
import finki.mk.uiktBackend.model.enums.ExamType;
import finki.mk.uiktBackend.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/get/{id}")
    public File getFile(@PathVariable Long id){
        return fileService.getFile(id);
    }

    @GetMapping("/{id}")
    public List<File> filesForSubject(@PathVariable Long id){
        return fileService.findApprovedFilesForSubject(id);
    }

    // Long type -> String examType
    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void uploadFiles(@PathVariable Long id, @RequestParam("examType") ExamType examType,
                            @RequestParam("files") List<MultipartFile> files){

        for(MultipartFile file : files){
            fileService.saveFile(id, file, examType);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id){

        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getContent()));
    }

    @GetMapping("/openFile/{id}")
    public ResponseEntity<ByteArrayResource> openFile(@PathVariable Long id){

        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getContent()));
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void approveFile(@PathVariable Long id){
        fileService.approveFile(id);
    }

    @GetMapping("/decline/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void declineFile(@PathVariable Long id){
        fileService.declineFile(id);
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<File> listAllPendingFiles(){
        return fileService.findAllPendingFiles();
    }

    @GetMapping("/declined")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<File> listAllDeclinedFiles(){
        return fileService.findAllDeclinedFiles();
    }
}
