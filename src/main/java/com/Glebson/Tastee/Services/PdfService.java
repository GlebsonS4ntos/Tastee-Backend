package com.Glebson.Tastee.Services;

import com.Glebson.Tastee.Domain.Post;
import com.Glebson.Tastee.Exceptions.NotFoundException;
import com.Glebson.Tastee.Exceptions.PdfException;
import com.Glebson.Tastee.Repositories.PostRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PdfService {

    private final PostRepository postRepository;

    public PdfService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public byte[] generatePostPdf(Long id){
        try{
            Post p = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post não encontrado."));

            InputStream reportJrxml = new ClassPathResource("reports/post.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportJrxml);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(p));

            Map<String, Object> parameters = new HashMap<>();
            JasperPrint report = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(report);
        }catch (Exception e){
            throw new PdfException(e.getMessage());
        }
    }
}
