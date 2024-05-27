package spr.springbooot2.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import spr.springbooot2.Repositoryy.Attachmentcontentrepository;
import spr.springbooot2.Repositoryy.Attechmentrepository;
import spr.springbooot2.spr.Attachmentcontent;
import spr.springbooot2.spr.Attechment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class AttanchmentController {

    private final Attechmentrepository attechmentrepository;
    private final Attachmentcontentrepository attachmentcontentrepository;
    @Autowired
    public AttanchmentController(Attechmentrepository attechmentrepository, Attachmentcontentrepository attachmentcontentrepository) {
        this.attechmentrepository = attechmentrepository;
        this.attachmentcontentrepository = attachmentcontentrepository;
    }
    @PostMapping
    public String attchment(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        String name = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        Attechment save = attechmentrepository.save(new Attechment(name, contentType, size));

        try {
            Attachmentcontent  attachmentcontent = new Attachmentcontent(file.getBytes(), save);
            attachmentcontentrepository.save(attachmentcontent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Attchment successful";
    }

    @PostMapping(value = "/uploadsys")
    public String uploadsys(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        String name = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        String[] cut = file.getOriginalFilename().split("\\.");
        System.out.println(cut[cut.length-1]);
        String s = UUID.randomUUID().toString();
        s = s+"."+cut[cut.length-1];
        System.out.println(s);
        Attechment save = attechmentrepository.save(new Attechment(name, contentType, size,s));
        Path path = Paths.get("saqlanganlar/"+s);
        Files.copy(file.getInputStream(),path);

        /*try {
            Attachmentcontent  attachmentcontent = new Attachmentcontent(file.getBytes(), save);
            attachmentcontentrepository.save(attachmentcontent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        return "Attchment successful : "+save.getId();
    }
    @GetMapping
    public List<Attachmentcontent> getAll() {
        return attachmentcontentrepository.findAll();
    }
    @GetMapping("/download/{id}")
    public void download(@PathVariable int id , HttpServletResponse response) throws IOException {
        Optional<Attechment> optionalAttechment = attechmentrepository.findById(id);
        if (optionalAttechment.isPresent()) {
            Attechment attechment = optionalAttechment.get();
            Optional<Attachmentcontent> attachmentcontent = attachmentcontentrepository.findById(attechment.getId());
            if (attachmentcontent.isPresent()) {
                Attachmentcontent attachment = attachmentcontent.get();
                response.setHeader("Content-Disposition","filename : "+ attechment.getName());
                response.setContentType(attechment.getContenttype());
                FileCopyUtils.copy(attachment.getFile(),response.getOutputStream());
            }

        }

    }
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        Optional<Attachmentcontent> attachmentcontent = attachmentcontentrepository.findById(id);
        if (attachmentcontent.isPresent()) {
            Optional<Attechment> attechment = attechmentrepository.findById(attachmentcontent.get().getId());
            if (attechment.isPresent()) {
                attachmentcontentrepository.delete(attachmentcontent.get());
                attechmentrepository.delete(attechment.get());
                return "Attachment delete  successful";
            }
        }
        return "Attachment not found";

    }


}
